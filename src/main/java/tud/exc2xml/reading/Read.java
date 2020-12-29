package tud.exc2xml.reading;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import tud.exc2xml.objects.Abstract;
import tud.exc2xml.objects.AlternativeMetadataReference;
import tud.exc2xml.objects.ApplicationSchemaInfo;
import tud.exc2xml.objects.ApplicationSchemaInformation;
import tud.exc2xml.objects.CellGeometry;
import tud.exc2xml.objects.Citation;
import tud.exc2xml.objects.Contact;
import tud.exc2xml.objects.DataIdentification;
import tud.exc2xml.objects.DataQualityInfo;
import tud.exc2xml.objects.DataSeries;
import tud.exc2xml.objects.DataStructure;
import tud.exc2xml.objects.DateInfo;
import tud.exc2xml.objects.DateStamp;
import tud.exc2xml.objects.DescriptiveKeywords;
import tud.exc2xml.objects.Dimension;
import tud.exc2xml.objects.DoiIdentifier;
import tud.exc2xml.objects.EquivalentScale;
import tud.exc2xml.objects.Extent;
import tud.exc2xml.objects.FileIdentifier;
import tud.exc2xml.objects.GeographicBoundingBox;
import tud.exc2xml.objects.GeometricObjects;
import tud.exc2xml.objects.Georectified;
import tud.exc2xml.objects.GridSpatialRepresentation;
import tud.exc2xml.objects.Identification;
import tud.exc2xml.objects.IdentificationInfo;
import tud.exc2xml.objects.Identifier;
import tud.exc2xml.objects.MetadataIdentifier;
import tud.exc2xml.objects.MetadataStandardName;
import tud.exc2xml.objects.Name;
import tud.exc2xml.objects.OnlineResource;
import tud.exc2xml.objects.ParentIdentifier;
import tud.exc2xml.objects.PointOfContact;
import tud.exc2xml.objects.ReferenceSystem;
import tud.exc2xml.objects.ReferenceSystemInfo;
import tud.exc2xml.objects.Resolution;
import tud.exc2xml.objects.Responsibility;
import tud.exc2xml.objects.Role;
import tud.exc2xml.objects.Scope;
import tud.exc2xml.objects.ServiceIdentification;
import tud.exc2xml.objects.SpatialRepresentation;
import tud.exc2xml.objects.SpatialRepresentationInfo;
import tud.exc2xml.objects.SpatialRepresentationType;
import tud.exc2xml.objects.SpatialResolution;
import tud.exc2xml.objects.StandaloneQualityReport;
import tud.exc2xml.objects.TemporalExtent;
import tud.exc2xml.objects.TemporalResolution;
import tud.exc2xml.objects.TransferOptions;
import tud.exc2xml.objects.TransformationParameter;
import tud.exc2xml.objects.VectorSpatialRepresentation;

/*
 * Read XSL file per rows and columns and create iso xml document.
 */
public class Read {
	public Integer refLineNo = 12;
	public File folder;
	public String folderName = "outputData";
	static Logger logger = Logger.getLogger(Read.class);

	static { }
	public Read() {}

	public File read(String fileName) {

		SimpleLayout layout = new SimpleLayout();
		FileAppender fileAppender = null;
		try {
			fileAppender = new FileAppender((Layout) layout, "logs/MeineLogDatei.log", false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		logger.addAppender((Appender) fileAppender);
		logger.setLevel(Level.ALL);
		logger.error((Object) "read");
		this.folder = Read.createTempDir((String) this.folderName);
		logger.error((Object) "after createTempDir");
		try {
			if (fileName.contains(".xl")) {

				logger.error((Object) ("filename: " + fileName));
				XSSFWorkbook wb = new XSSFWorkbook((InputStream) new FileInputStream(fileName)); 
				this.readExcelFile(wb); 
			} else if (fileName.contains(".csv")) {
				csvToXLSX(fileName);
			}

		} catch (FileNotFoundException e) {
			logger.error((Object) "FileNotFoundException");
		} catch (IOException e) {
			logger.error((Object) "IOException");
		}

		return this.folder;
	}

	public void csvToXLSX(String csvPath) {
		try {
			String csvFileAddress = csvPath; // csv file address
			XSSFWorkbook workBook = new XSSFWorkbook();
			XSSFSheet sheet = workBook.createSheet("sheet1");
			String currentLine = null;
			int RowNum = 0;
			BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
			while ((currentLine = br.readLine()) != null) {
				String str[] = currentLine.split(",");
				RowNum++;
				XSSFRow currentRow = sheet.createRow(RowNum);
				for (int i = 0; i < str.length; i++) {
					currentRow.createCell(i).setCellValue(str[i]);
				}
			}
			this.readExcelFile(workBook);
			
			br.close();
			workBook.close(); 
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Exception in try");
		}
	}

	public void readExcelFile(XSSFWorkbook wb) throws IOException {
		XSSFSheet sheet = wb.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows(); 
		for (int r = 13; r < rows; ++r) {
			CellGeometry cellGeometry = new CellGeometry();
			Citation citation = new Citation();
			Contact contact = new Contact();
			DataIdentification identificationInfoDataIdentification = new DataIdentification();
			DateInfo dateInfo = new DateInfo();
			ArrayList<DescriptiveKeywords> descriptiveKeywords = new ArrayList<DescriptiveKeywords>();
			Dimension dimension = new Dimension();
			EquivalentScale equivalentScale = new EquivalentScale();
			Extent extent = new Extent();
			FileIdentifier fileIdentifier = new FileIdentifier();
			GeographicBoundingBox geographicBoundingBox = new GeographicBoundingBox();
			GeometricObjects geometricObjects = new GeometricObjects();
			Identifier identifier = new Identifier();
			MetadataStandardName metadataStandardName = new MetadataStandardName();
			ArrayList<OnlineResource> onlineResource = new ArrayList<OnlineResource>();
			ParentIdentifier parentIdentifier = new ParentIdentifier();
			PointOfContact pointOfContact = new PointOfContact();
			ReferenceSystemInfo referenceSystemInfo = new ReferenceSystemInfo();
			Resolution resolution = new Resolution();
			SpatialRepresentation spatialRepresentation = new SpatialRepresentation();
			SpatialResolution spatialResolution = new SpatialResolution();
			TemporalExtent temporalExtent = new TemporalExtent();
			TemporalResolution temporalResolution = new TemporalResolution();
			TransformationParameter transformationParameter = new TransformationParameter();
			VectorSpatialRepresentation vectorSpatialRepresentation = new VectorSpatialRepresentation();
			MetadataIdentifier metadataIdentifier = new MetadataIdentifier();
			IdentificationInfo identificationInfo = new IdentificationInfo();
			Abstract abstractText = new Abstract();
			Identification identificationInfoIdentification = new Identification();
			ServiceIdentification identificationInfoServiceIdentification = new ServiceIdentification();
			Responsibility contactResponsibility = new Responsibility();
			DateStamp dateInfoDate = new DateStamp();
			AlternativeMetadataReference alternativeMetadataReference = new AlternativeMetadataReference();
			SpatialRepresentationInfo spatialRepresentationInfo = new SpatialRepresentationInfo();
			GridSpatialRepresentation gridSpatialRepresentation = new GridSpatialRepresentation();
			Georectified georectified = new Georectified();
			ReferenceSystem referenceSystem = new ReferenceSystem();
			DataQualityInfo dataQualityInfo = new DataQualityInfo();
			ApplicationSchemaInfo applicationSchemaInfo = new ApplicationSchemaInfo();
			ApplicationSchemaInformation applicationSchemaInformation = new ApplicationSchemaInformation();
			Role role = new Role();
			SpatialRepresentationType spatialRepresentationType = new SpatialRepresentationType();
			Scope scope = new Scope();
			StandaloneQualityReport standaloneQualityReport = new StandaloneQualityReport();
			Name name = new Name();
			DoiIdentifier doiIdentifier = new DoiIdentifier();

			XSSFRow row = sheet.getRow(r);
			if (row == null)
				continue;
			int cells = row.getPhysicalNumberOfCells();
			Short num = row.getLastCellNum();
			System.out.println("--------------------------------------------");
			System.out.println("----- NO of CELLS " + cells + " LCN " + num);
			System.out.println("--------------------------------------------");
			cells = num.shortValue();

			for (int c = 0; c < cells; ++c) {
				String[] keywords;
				String[] url;
				TransferOptions transferOption;
				XSSFCell cell = row.getCell(c);
				String value = "";
				if (cell == null)
					continue;
				switch (cell.getCellType()) {
				case FORMULA: {
					value = cell.getCellFormula();
					break;
				}
				case NUMERIC: {
					value = String.valueOf(cell.getNumericCellValue());
					break;
				}
				case STRING: {
					value = cell.getStringCellValue();
				}
				default:
					break;
				}
				XSSFCell refCell = sheet.getRow(this.refLineNo.intValue()).getCell(c);
				XSSFCell preRefCell = null;
				if (c > 0) {
					preRefCell = sheet.getRow(this.refLineNo.intValue()).getCell(c - 1);
				}
				XSSFCell refCellHeader = sheet.getRow(this.refLineNo - 1).getCell(c);
				System.out.println("--------------------------------------------");
				System.out.println("ROW: " + row.getRowNum() + " CELL:" + cell.getColumnIndex() + " " + value + ".");
				if (value.equals(""))
					continue;

				if (refCell.getStringCellValue().equals("Base id")) {

					String id = cell.getStringCellValue();
					String codeSpace = "urn:" + id + ":metadata:dataset";
					String code = "";
					id = String.valueOf(id) + ":metadata:dataset";
					if (row.getCell(c + 3) == null || row.getCell(c + 3).getStringCellValue().equals("")) {
						id = String.valueOf(id) + ":" + row.getCell(c + 1).getStringCellValue().toLowerCase();
						code = row.getCell(c + 1).getStringCellValue().toLowerCase();
					} else if (row.getCell(c + 2) != null) {
						id = String.valueOf(id) + ":" + row.getCell(c + 2).getStringCellValue().toLowerCase() + "-"
								+ row.getCell(c + 3).getStringCellValue().toLowerCase();
						code = String.valueOf(code) + row.getCell(c + 2).getStringCellValue().toLowerCase() + "-"
								+ row.getCell(c + 3).getStringCellValue().toLowerCase();
					}
					id = id.replace(" ", "");
					id = id.replace(",", "-");
					id = id.replace("/", "-");
					code = code.replace(" ", "");
					code = code.replace(",", "-");
					code = code.replace("/", "-");
					codeSpace = codeSpace.replace(" ", "");
					codeSpace = codeSpace.replace(",", "-");
					codeSpace = codeSpace.replace("/", "-"); 
					fileIdentifier.setFileIdentifier(id);
					identifier.setNameSpace(codeSpace);
					identifier.setCode(code);
					continue;
				}
				if (refCell.getStringCellValue().equals("Data series")) {
					continue;
				}
				if (refCell.getStringCellValue().equals("Structure in data series")) {
					continue;
				}
				if (refCell.getStringCellValue().equals("Title")) {
					citation.setTitle(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Dataset DOI")) {
					doiIdentifier.setDataset(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Publication DOI")) {
					doiIdentifier.setPublication(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Prov-o Link")) 
					continue;
				if (refCell.getStringCellValue().equals("Abstract")) {
					abstractText.setAbstractText(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("OrcID")) {
					contactResponsibility.setOrcID(value);
					continue;
				}
				if (refCellHeader.getStringCellValue().equals("Reference system info")) {
					referenceSystem.setCode(value);
					referenceSystem.setNameSpace(row.getCell(c + 1).getStringCellValue());
					referenceSystem.setVersion(row.getCell(c + 2).getStringCellValue());
					continue;
				}
				if (refCell.getStringCellValue().equals("West bound longitude")) {
					geographicBoundingBox.setWestBoundLongitude(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("East bound longitude")) {
					geographicBoundingBox.setEastBoundLongitude(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("North bound latitude")) {
					geographicBoundingBox.setNorthBoundLatitude(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("South bound latitude")) {
					geographicBoundingBox.setSouthBoundLatitude(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Begin")) {
					temporalExtent.setBegin(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("End")) {
					temporalExtent.setEnd(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Temporal Resolution")) {
					temporalResolution.setPeriodDuration(value);
					continue;
				}
				// Description
				if (refCell.getStringCellValue().equals("Eqivalent scale")) {
					equivalentScale.setDenominator(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Distance")) {
					spatialResolution.setDistance(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Vertical")) {
					spatialResolution.setVertical(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Angular Distance")) {
					spatialResolution.setAngularDistance(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Level of detail")) {
					spatialResolution.setLevelOfDetail(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Number of dimensions")) {
					spatialRepresentation.setNumberOfDimensions(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Dimension size")) {
					dimension.setDimensionSize(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Dimension name")) {
					dimension.setDimensionName(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Resolution")) {
					resolution.setResolution(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Cell geometry")) {
					cellGeometry.setCellGeometry(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Transformation parameter")) {
					transformationParameter.setTransformationParameter(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Topology level")) {
					vectorSpatialRepresentation.setTopologyLevel(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Geometric object type")) {
					geometricObjects.setGeometricObjectType(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Geometric object count")) {
					geometricObjects.setGeometricObjectCount(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Spatial representation type")) {
					spatialRepresentationType.setSpatialRepresentationType(value);
					continue;
				}
				// header: identifier
				if (refCellHeader.getStringCellValue().equals("Identifier")) {
					identifier.setCode(value);
					identifier.setNameSpace(row.getCell(c + 1).getStringCellValue());
					identifier.setVersion(row.getCell(c + 2).getStringCellValue());
					continue;
				}
				// descriptive keywords
				if (refCell.getStringCellValue().equals("Descriptive keyword")) {
					keywords = value.split(",");
					for (int i = 0; i < keywords.length; ++i) {
						DescriptiveKeywords descriptiveKeyword = new DescriptiveKeywords();
						descriptiveKeyword.setDescriptiveKeyword(keywords[i].trim());
						if (row.getCell(c + 1) != null) {
							descriptiveKeyword.setThesaurusName(row.getCell(c + 1).getStringCellValue());
						}
						if (row.getCell(c + 2) != null) {
							descriptiveKeyword.setKeywordClass(row.getCell(c + 2).getStringCellValue());
						}
						if (row.getCell(c + 3) != null) {
							descriptiveKeyword.setConceptIdentifier(row.getCell(c + 3).getStringCellValue());
						}
						if (row.getCell(c + 4) != null) {
							descriptiveKeyword.setOntologyName(row.getCell(c + 4).getStringCellValue());
						}
						if (row.getCell(c + 5) != null) {
							descriptiveKeyword.setOntologyURL(row.getCell(c + 5).getStringCellValue());
						}

						descriptiveKeywords.add(descriptiveKeyword);
					}
					continue;
				}
				if (refCell.getStringCellValue().equals("Keyword class")
						|| refCell.getStringCellValue().equals("Thesaurus name"))
					continue;
				if (refCell.getStringCellValue().equals("Concept identifier (URI)"))
					continue;
				if (refCell.getStringCellValue().equals("Ontology name"))
					continue;
				if (refCell.getStringCellValue().equals("Ontology url"))
					continue;
				
				// Geodata link
				if (refCell.getStringCellValue().equals("Path")) {
					url = value.split(",");
					for (int i = 0; i < url.length; ++i) {
						OnlineResource geodataLink = new OnlineResource();
						geodataLink.setLinkage(url[i].trim());
						if (row.getCell(c + 1) != null) 
							geodataLink.setName(row.getCell(c + 1).getStringCellValue());
						if (row.getCell(c + 2) != null) 
							geodataLink.setDescription(row.getCell(c + 2).getStringCellValue());
						if (row.getCell(c + 3) != null) 
							geodataLink.setFunction(row.getCell(c + 3).getStringCellValue());
						onlineResource.add(geodataLink);
					}
					continue;
				}

				if (refCell.getStringCellValue().equals("Resource name"))
					continue;
				if (refCell.getStringCellValue().equals("Description"))
					continue;
				if (refCell.getStringCellValue().equals("Function"))
					continue;
				if (refCell.getStringCellValue().equals("Equivalent scale")) {
					continue;
				}
				if (refCell.getStringCellValue().equals("Distance")) {
					continue;
				}
				if (refCell.getStringCellValue().equals("Unit of measurement")) {
					continue;
				}
				if (refCell.getStringCellValue().equals("Parent identifier")) {
					parentIdentifier.setParentIdentifier(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Date stamp")) {
					dateInfoDate.setDate(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Role")) {
					role.setRole(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Individual Name")) {
					contactResponsibility.setIndividualName(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Organisation Name")) {
					contactResponsibility.setOrganisation(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Position")) {
					contactResponsibility.setPosition(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Phone")) {
					contactResponsibility.setPhone(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("E-Mail address")) {
					contactResponsibility.setMail(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Schema name")) {
					citation.setTitle(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("URL")) {
					continue;
				}
				if (refCell.getStringCellValue().equals("Metadata standard name")) {
					metadataStandardName.setName(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Metadata standard version")) {
					citation.setEdition(value);
					continue;
				}
				if (refCell.getStringCellValue().equals("Metadata Schema URL")) {
					continue;
				}
			}

			contactResponsibility.setOnlineResource(onlineResource);
			contactResponsibility.setRole(role);
			contact.setResponsibility(contactResponsibility);
			dateInfo.setDateInfoDate(dateInfoDate);
			citation.setDateInfoDate(dateInfoDate);
			citation.setDoiIdentifier(doiIdentifier);
			citation.setContactResponsibility(contactResponsibility);
			citation.setOnlineResource(onlineResource);
			alternativeMetadataReference.setCitation(citation);
			gridSpatialRepresentation.setSpatialRepresentation(spatialRepresentation);
			gridSpatialRepresentation.setDimension(dimension);
			gridSpatialRepresentation.setCellGeometry(cellGeometry);
			gridSpatialRepresentation.setTransformationParameter(transformationParameter);
			spatialRepresentationInfo.setGridSpatialRepresentation(gridSpatialRepresentation);
			georectified.setSpatialRepresentation(spatialRepresentation);
			georectified.setDimension(dimension);
			georectified.setCellGeometry(cellGeometry);
			georectified.setTransformationParameter(transformationParameter);
			spatialRepresentationInfo.setGeorectified(georectified);
			vectorSpatialRepresentation.setGeometricObjects(geometricObjects);
			spatialRepresentationInfo.setVectorSpatialRepresentation(vectorSpatialRepresentation);
			referenceSystemInfo.setReferenceSystem(referenceSystem);
			identificationInfoIdentification.setCitation(citation);
			identificationInfoIdentification.setAbstractText(abstractText);
			pointOfContact.setContactResponsibility(contactResponsibility);
			identificationInfoIdentification.setPointOfContact(pointOfContact);
			spatialResolution.setEquivalentScale(equivalentScale);
			identificationInfoIdentification.setSpatialResolution(spatialResolution);
			identificationInfoIdentification.setTemporalResolution(temporalResolution);
			extent.setTemporalExtent(temporalExtent);
			geographicBoundingBox.setIdentifier(identifier);
			extent.setGeographicBoundingBox(geographicBoundingBox);
			identificationInfoIdentification.setDescriptiveKeywords(descriptiveKeywords);
			identificationInfo.setIdentification(identificationInfoIdentification);
			identificationInfoDataIdentification.setCitation(citation);
			identificationInfoDataIdentification.setAbstractText(abstractText);
			identificationInfoDataIdentification.setPointOfContact(pointOfContact);
			identificationInfoDataIdentification.setSpatialResolution(spatialResolution);
			identificationInfoDataIdentification.setTemporalResolution(temporalResolution);
			identificationInfoDataIdentification.setExtent(extent);
			identificationInfoDataIdentification.setDescriptiveKeywords(descriptiveKeywords);
			identificationInfoDataIdentification.setSpatialRepresentationType(spatialRepresentationType);
			identificationInfo.setDataIdentification(identificationInfoDataIdentification);
			identificationInfoServiceIdentification.setCitation(citation);
			identificationInfoServiceIdentification.setAbstractText(abstractText);
			identificationInfoServiceIdentification.setPointOfContact(pointOfContact);
			identificationInfoServiceIdentification.setSpatialResolution(spatialResolution);
			identificationInfoServiceIdentification.setTemporalResolution(temporalResolution);
			identificationInfoServiceIdentification.setExtent(extent);
			identificationInfoServiceIdentification.setDescriptiveKeywords(descriptiveKeywords);
			identificationInfo.setServiceIdentification(identificationInfoServiceIdentification);
			dataQualityInfo.setScope(scope);
			standaloneQualityReport.setAbstractText(abstractText);
			standaloneQualityReport.setCitation(citation);
			dataQualityInfo.setStandaloneQualityReport(standaloneQualityReport);
			applicationSchemaInformation.setName(name);
			applicationSchemaInfo.setApplicationSchemaInformation(applicationSchemaInformation);

			String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><mda:DS_Resource> xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" "
					+ "xmlns:srv=\"http://standards.iso.org/iso/19115/-3/srv/2.0\" xmlns:msr=\"http://standards.iso.org/iso/19115/-3/msr/1.0\" "
					+ "xmlns:mrs=\"http://standards.iso.org/iso/19115/-3/mrs/1.0\" xmlns:mrd=\"http://standards.iso.org/iso/19115/-3/mrd/1.0\" "
					+ "xmlns:mrc=\"http://standards.iso.org/iso/19115/-3/mrc/1.0\" xmlns:mpc=\"http://standards.iso.org/iso/19115/-3/mpc/1.0\" "
					+ "xmlns:mmi=\"http://standards.iso.org/iso/19115/-3/mmi/1.0\" xmlns:mex=\"http://standards.iso.org/iso/19115/-3/mex/1.0\" "
					+ "xmlns:mdt=\"http://standards.iso.org/iso/19115/-3/mdt/1.0\" xmlns:mds=\"http://standards.iso.org/iso/19115/-3/mds/1.0\" "
					+ "xmlns:mdq=\"http://standards.iso.org/iso/19157/-2/mdq/1.0\" xmlns:mdb=\"http://standards.iso.org/iso/19115/-3/mdb/1.0\" "
					+ "xmlns:md2=\"http://standards.iso.org/iso/19115/-3/md2/1.0\" xmlns:md1=\"http://standards.iso.org/iso/19115/-3/md1/1.0\" "
					+ "xmlns:mco=\"http://standards.iso.org/iso/19115/-3/mco/1.0\" xmlns:mcc=\"http://standards.iso.org/iso/19115/-3/mcc/1.0\" "
					+ "xmlns:mas=\"http://standards.iso.org/iso/19115/-3/mas/1.0\" xmlns:mac=\"http://standards.iso.org/iso/19115/-3/mac/1.0\" "
					+ "xmlns:lan=\"http://standards.iso.org/iso/19115/-3/lan/1.0\" xmlns:gmw=\"http://standards.iso.org/iso/19115/-3/gmw/1.0\" "
					+ "xmlns:gex=\"http://standards.iso.org/iso/19115/-3/gex/1.0\" xmlns:gcx=\"http://standards.iso.org/iso/19115/-3/gcx/1.0\" "
					+ "xmlns:gco=\"http://standards.iso.org/iso/19115/-3/gco/1.0\" xmlns:dqm=\"http://standards.iso.org/iso/19157/-2/dqm/1.0\" "
					+ "xmlns:dqc=\"http://standards.iso.org/iso/19157/-2/dqc/1.0\" xmlns:cit=\"http://standards.iso.org/iso/19115/-3/cit/1.0\" "
					+ "xmlns:cat=\"http://standards.iso.org/iso/19115/-3/cat/1.0\" xmlns:mda=\"http://standards.iso.org/iso/19115/-3/mda/1.0\" "
					+ "<mdb:MD_Metadata>";

			xmlString = String.valueOf(xmlString) + metadataIdentifier.toString() + contact.toString()
					+ dateInfo.toString() + alternativeMetadataReference.toString()
					+ spatialRepresentationInfo.toString() + referenceSystemInfo.toString()
					+ identificationInfo.toString() + dataQualityInfo.toString() + applicationSchemaInfo.toString()
					+ "</mdb:MD_Metadata></mda:DS_Resource>";

			xmlString = xmlString.replace("\u00e4", "ae");
			xmlString = xmlString.replace("\u00fc", "ue");
			xmlString = xmlString.replace("\u00f6", "oe");
			xmlString = xmlString.replace("\u00df", "ss");
			xmlString = xmlString.replace("&", "&#38;"); 
			
			if (!fileIdentifier.toString().equals("")) {
				String name2 = fileIdentifier.getFileIdentifier();
				name2 = name2.replaceAll(":", "");
				name2 = name2.replaceAll("-", "-");
				this.buildXMLFile(xmlString, this.folder.getPath(), name2);
			}

		}
	}

	public void buildXMLFile(String xmlString, String folderName, String fileName) {
		try {
			System.out.println("buildXML");
			logger.error((Object) "buildXMLFile");
			logger.error((Object) xmlString);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			builder = factory.newDocumentBuilder();
			byte[] stringBytes = xmlString.getBytes();
			ByteArrayInputStream bais = new ByteArrayInputStream(stringBytes);
			Document document = builder.parse(bais);
			DOMSource source = new DOMSource(document);
			System.out.println("FILENAME: " + fileName);
			logger.info((Object) ("filename: " + fileName));
			File file = File.createTempFile("GeneratedXML_" + fileName, ".xml", this.folder);
			logger.info((Object) "file created.");
			FileOutputStream resultDoc = null;
			resultDoc = new FileOutputStream(file);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, new StreamResult(resultDoc));
			logger.info((Object) "file filled.");
		} catch (ParserConfigurationException e) {
			logger.error((Object) "ParserConfigurationException");
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (SAXException e) {
			logger.error((Object) "SAXException");
			logger.error((Object) e.getMessage());
			System.err.println(e.getMessage());
			e.printStackTrace();
			logger.error((Object) xmlString);
		} catch (IOException e) {
			logger.error((Object) "IOException");
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			logger.error((Object) "TransformerConfigurationException");
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			logger.error((Object) "TransformerConfigurationException");
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (TransformerException e) {
			logger.error((Object) "TransformerException");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static File createTempDir(String foldername) {
		logger.error((Object) "createTempDir");
		String baseTempPath = System.getProperty("java.io.tmpdir");
		Calendar now = Calendar.getInstance();
		File tempDir = new File(
				String.valueOf(baseTempPath) + File.separator + foldername + "_" + now.getTimeInMillis());
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		logger.error((Object) "makeDir finished");
		tempDir.deleteOnExit();
		return tempDir;
	}

	public static String printXML(Document doc) {
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
			return "";
		}
	}
}
