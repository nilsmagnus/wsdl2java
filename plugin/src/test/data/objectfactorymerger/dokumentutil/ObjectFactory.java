
package dk.jyskebank.service.dokumentutil.ws;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dk.jyskebank.service.dokumentutil.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _MessageHeader_QNAME = new QName("http://v1.messageheader.esv.bankdata.dk", "MessageHeader");
    private final static QName _DocumentInfoList_QNAME = new QName("http://did.esv.bankdata.dk", "DocumentInfoList");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _DocumentTypeList_QNAME = new QName("http://did.esv.bankdata.dk", "DocumentTypeList");
    private final static QName _ReferenceNumberList_QNAME = new QName("http://did.esv.bankdata.dk", "ReferenceNumberList");
    private final static QName _DocumentType_QNAME = new QName("http://did.esv.bankdata.dk", "DocumentType");
    private final static QName _DocumentTypes_QNAME = new QName("http://did.esv.bankdata.dk", "DocumentTypes");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _HentDokumentSvarConvres_QNAME = new QName("http://schemas.datacontract.org/2004/07/BD.DID.DokumentUtil.Generated", "hentDokumentSvarConvres");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _DocumentInfo_QNAME = new QName("http://did.esv.bankdata.dk", "DocumentInfo");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _HentDokumentSvar_QNAME = new QName("http://schemas.datacontract.org/2004/07/BD.DID.DokumentUtil.Generated", "hentDokumentSvar");
    private final static QName _ArchivePdfDocument_QNAME = new QName("http://did.esv.bankdata.dk", "ArchivePdfDocument");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _ParameterList_QNAME = new QName("http://did.esv.bankdata.dk", "ParameterList");
    private final static QName _Parameter_QNAME = new QName("http://did.esv.bankdata.dk", "Parameter");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _GetDocumentTypes_QNAME = new QName("http://did.esv.bankdata.dk", "GetDocumentTypes");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _FieldList_QNAME = new QName("http://did.esv.bankdata.dk", "FieldList");
    private final static QName _HentDokumentResp_QNAME = new QName("http://did.esv.bankdata.dk", "HentDokumentResp");
    private final static QName _HentDokumentSvarConvresNettrans_QNAME = new QName("http://schemas.datacontract.org/2004/07/BD.DID.DokumentUtil.Generated", "hentDokumentSvarConvresNettrans");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _ArchivedPdfDocument_QNAME = new QName("http://did.esv.bankdata.dk", "ArchivedPdfDocument");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _Field_QNAME = new QName("http://did.esv.bankdata.dk", "Field");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _HentDokumentReqBody_QNAME = new QName("http://did.esv.bankdata.dk", "HentDokumentReqBody");
    private final static QName _DocumentArea_QNAME = new QName("http://did.esv.bankdata.dk", "DocumentArea");
    private final static QName _HentDokumentRequestBody_QNAME = new QName("http://schemas.datacontract.org/2004/07/BD.ESV.DID.DokumentUtil", "HentDokumentRequestBody");
    private final static QName _DocumentAreaList_QNAME = new QName("http://did.esv.bankdata.dk", "DocumentAreaList");
    private final static QName _ParameterKey_QNAME = new QName("http://did.esv.bankdata.dk", "Key");
    private final static QName _ParameterValue_QNAME = new QName("http://did.esv.bankdata.dk", "Value");
    private final static QName _DocumentAreaName_QNAME = new QName("http://did.esv.bankdata.dk", "Name");
    private final static QName _DocumentAreaId_QNAME = new QName("http://did.esv.bankdata.dk", "Id");
    private final static QName _ArchivePdfDocumentDocument_QNAME = new QName("http://did.esv.bankdata.dk", "Document");
    private final static QName _DocumentTypeShowCustomerCode_QNAME = new QName("http://did.esv.bankdata.dk", "ShowCustomerCode");
    private final static QName _DocumentTypeMessage_QNAME = new QName("http://did.esv.bankdata.dk", "Message");
    private final static QName _DocumentTypePrintId_QNAME = new QName("http://did.esv.bankdata.dk", "PrintId");
    private final static QName _DocumentTypeDocumentTypeId_QNAME = new QName("http://did.esv.bankdata.dk", "DocumentTypeId");
    private final static QName _DocumentTypeDocumentTypeName_QNAME = new QName("http://did.esv.bankdata.dk", "DocumentTypeName");
    private final static QName _DocumentTypeAccessOnlineBankingCode_QNAME = new QName("http://did.esv.bankdata.dk", "AccessOnlineBankingCode");
    private final static QName _DocumentTypeDocumentAreaId_QNAME = new QName("http://did.esv.bankdata.dk", "DocumentAreaId");
    private final static QName _FieldTypeCodeText_QNAME = new QName("http://did.esv.bankdata.dk", "TypeCodeText");
    private final static QName _FieldObltorCode_QNAME = new QName("http://did.esv.bankdata.dk", "ObltorCode");
    private final static QName _FieldSortNumber_QNAME = new QName("http://did.esv.bankdata.dk", "SortNumber");
    private final static QName _FieldFieldName_QNAME = new QName("http://did.esv.bankdata.dk", "FieldName");
    private final static QName _FieldPlaceCode_QNAME = new QName("http://did.esv.bankdata.dk", "PlaceCode");
    private final static QName _HentDokumentRequestBodyUserId_QNAME = new QName("http://schemas.datacontract.org/2004/07/BD.ESV.DID.DokumentUtil", "userId");
    private final static QName _HentDokumentRequestBodyDokId_QNAME = new QName("http://schemas.datacontract.org/2004/07/BD.ESV.DID.DokumentUtil", "dokId");
    private final static QName _HentDokumentRequestBodyBankNr_QNAME = new QName("http://schemas.datacontract.org/2004/07/BD.ESV.DID.DokumentUtil", "bankNr");
    private final static QName _HentDokumentRequestBodyRefNr_QNAME = new QName("http://schemas.datacontract.org/2004/07/BD.ESV.DID.DokumentUtil", "refNr");
    private final static QName _DocumentInfoReferenceNumber_QNAME = new QName("http://did.esv.bankdata.dk", "ReferenceNumber");
    private final static QName _DocumentInfoDocumentId_QNAME = new QName("http://did.esv.bankdata.dk", "DocumentId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dk.jyskebank.service.dokumentutil.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Parameter }
     * 
     */
    public Parameter createParameter() {
        return new Parameter();
    }

    /**
     * Create an instance of {@link DocumentAreaList }
     * 
     */
    public DocumentAreaList createDocumentAreaList() {
        return new DocumentAreaList();
    }

    /**
     * Create an instance of {@link DocumentType }
     * 
     */
    public DocumentType createDocumentType() {
        return new DocumentType();
    }

    /**
     * Create an instance of {@link GetDocumentTypes }
     * 
     */
    public GetDocumentTypes createGetDocumentTypes() {
        return new GetDocumentTypes();
    }

    /**
     * Create an instance of {@link HentDokumentSvarConvres }
     * 
     */
    public HentDokumentSvarConvres createHentDokumentSvarConvres() {
        return new HentDokumentSvarConvres();
    }

    /**
     * Create an instance of {@link Field }
     * 
     */
    public Field createField() {
        return new Field();
    }

    /**
     * Create an instance of {@link DocumentTypes }
     * 
     */
    public DocumentTypes createDocumentTypes() {
        return new DocumentTypes();
    }

    /**
     * Create an instance of {@link ReferenceNumberList }
     * 
     */
    public ReferenceNumberList createReferenceNumberList() {
        return new ReferenceNumberList();
    }

    /**
     * Create an instance of {@link HentDokumentRequestBody }
     * 
     */
    public HentDokumentRequestBody createHentDokumentRequestBody() {
        return new HentDokumentRequestBody();
    }

    /**
     * Create an instance of {@link HentDokumentSvar }
     * 
     */
    public HentDokumentSvar createHentDokumentSvar() {
        return new HentDokumentSvar();
    }

    /**
     * Create an instance of {@link ParameterList }
     * 
     */
    public ParameterList createParameterList() {
        return new ParameterList();
    }

    /**
     * Create an instance of {@link MessageHeader }
     * 
     */
    public MessageHeader createMessageHeader() {
        return new MessageHeader();
    }

    /**
     * Create an instance of {@link ArchivePdfDocumentRequest }
     * 
     */
    public ArchivePdfDocumentRequest createArchivePdfDocumentRequest() {
        return new ArchivePdfDocumentRequest();
    }

    /**
     * Create an instance of {@link DocumentInfo }
     * 
     */
    public DocumentInfo createDocumentInfo() {
        return new DocumentInfo();
    }

    /**
     * Create an instance of {@link DocumentTypeList }
     * 
     */
    public DocumentTypeList createDocumentTypeList() {
        return new DocumentTypeList();
    }

    /**
     * Create an instance of {@link DocumentArea }
     * 
     */
    public DocumentArea createDocumentArea() {
        return new DocumentArea();
    }

    /**
     * Create an instance of {@link FieldList }
     * 
     */
    public FieldList createFieldList() {
        return new FieldList();
    }

    /**
     * Create an instance of {@link ArchivePdfDocument }
     * 
     */
    public ArchivePdfDocument createArchivePdfDocument() {
        return new ArchivePdfDocument();
    }

    /**
     * Create an instance of {@link ArchivedPdfDocument }
     * 
     */
    public ArchivedPdfDocument createArchivedPdfDocument() {
        return new ArchivedPdfDocument();
    }

    /**
     * Create an instance of {@link HentDokumentSvarConvresNettrans }
     * 
     */
    public HentDokumentSvarConvresNettrans createHentDokumentSvarConvresNettrans() {
        return new HentDokumentSvarConvresNettrans();
    }

    /**
     * Create an instance of {@link DocumentInfoList }
     * 
     */
    public DocumentInfoList createDocumentInfoList() {
        return new DocumentInfoList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://v1.messageheader.esv.bankdata.dk", name = "MessageHeader")
    public JAXBElement<MessageHeader> createMessageHeader(MessageHeader value) {
        return new JAXBElement<MessageHeader>(_MessageHeader_QNAME, MessageHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentInfoList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentInfoList")
    public JAXBElement<DocumentInfoList> createDocumentInfoList(DocumentInfoList value) {
        return new JAXBElement<DocumentInfoList>(_DocumentInfoList_QNAME, DocumentInfoList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentTypeList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentTypeList")
    public JAXBElement<DocumentTypeList> createDocumentTypeList(DocumentTypeList value) {
        return new JAXBElement<DocumentTypeList>(_DocumentTypeList_QNAME, DocumentTypeList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceNumberList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "ReferenceNumberList")
    public JAXBElement<ReferenceNumberList> createReferenceNumberList(ReferenceNumberList value) {
        return new JAXBElement<ReferenceNumberList>(_ReferenceNumberList_QNAME, ReferenceNumberList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentType")
    public JAXBElement<DocumentType> createDocumentType(DocumentType value) {
        return new JAXBElement<DocumentType>(_DocumentType_QNAME, DocumentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentTypes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentTypes")
    public JAXBElement<DocumentTypes> createDocumentTypes(DocumentTypes value) {
        return new JAXBElement<DocumentTypes>(_DocumentTypes_QNAME, DocumentTypes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HentDokumentSvarConvres }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BD.DID.DokumentUtil.Generated", name = "hentDokumentSvarConvres")
    public JAXBElement<HentDokumentSvarConvres> createHentDokumentSvarConvres(HentDokumentSvarConvres value) {
        return new JAXBElement<HentDokumentSvarConvres>(_HentDokumentSvarConvres_QNAME, HentDokumentSvarConvres.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentInfo")
    public JAXBElement<DocumentInfo> createDocumentInfo(DocumentInfo value) {
        return new JAXBElement<DocumentInfo>(_DocumentInfo_QNAME, DocumentInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HentDokumentSvar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BD.DID.DokumentUtil.Generated", name = "hentDokumentSvar")
    public JAXBElement<HentDokumentSvar> createHentDokumentSvar(HentDokumentSvar value) {
        return new JAXBElement<HentDokumentSvar>(_HentDokumentSvar_QNAME, HentDokumentSvar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArchivePdfDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "ArchivePdfDocument")
    public JAXBElement<ArchivePdfDocument> createArchivePdfDocument(ArchivePdfDocument value) {
        return new JAXBElement<ArchivePdfDocument>(_ArchivePdfDocument_QNAME, ArchivePdfDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParameterList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "ParameterList")
    public JAXBElement<ParameterList> createParameterList(ParameterList value) {
        return new JAXBElement<ParameterList>(_ParameterList_QNAME, ParameterList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Parameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "Parameter")
    public JAXBElement<Parameter> createParameter(Parameter value) {
        return new JAXBElement<Parameter>(_Parameter_QNAME, Parameter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDocumentTypes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "GetDocumentTypes")
    public JAXBElement<GetDocumentTypes> createGetDocumentTypes(GetDocumentTypes value) {
        return new JAXBElement<GetDocumentTypes>(_GetDocumentTypes_QNAME, GetDocumentTypes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FieldList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "FieldList")
    public JAXBElement<FieldList> createFieldList(FieldList value) {
        return new JAXBElement<FieldList>(_FieldList_QNAME, FieldList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HentDokumentSvar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "HentDokumentResp")
    public JAXBElement<HentDokumentSvar> createHentDokumentResp(HentDokumentSvar value) {
        return new JAXBElement<HentDokumentSvar>(_HentDokumentResp_QNAME, HentDokumentSvar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HentDokumentSvarConvresNettrans }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BD.DID.DokumentUtil.Generated", name = "hentDokumentSvarConvresNettrans")
    public JAXBElement<HentDokumentSvarConvresNettrans> createHentDokumentSvarConvresNettrans(HentDokumentSvarConvresNettrans value) {
        return new JAXBElement<HentDokumentSvarConvresNettrans>(_HentDokumentSvarConvresNettrans_QNAME, HentDokumentSvarConvresNettrans.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArchivedPdfDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "ArchivedPdfDocument")
    public JAXBElement<ArchivedPdfDocument> createArchivedPdfDocument(ArchivedPdfDocument value) {
        return new JAXBElement<ArchivedPdfDocument>(_ArchivedPdfDocument_QNAME, ArchivedPdfDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Field }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "Field")
    public JAXBElement<Field> createField(Field value) {
        return new JAXBElement<Field>(_Field_QNAME, Field.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HentDokumentRequestBody }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "HentDokumentReqBody")
    public JAXBElement<HentDokumentRequestBody> createHentDokumentReqBody(HentDokumentRequestBody value) {
        return new JAXBElement<HentDokumentRequestBody>(_HentDokumentReqBody_QNAME, HentDokumentRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentArea }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentArea")
    public JAXBElement<DocumentArea> createDocumentArea(DocumentArea value) {
        return new JAXBElement<DocumentArea>(_DocumentArea_QNAME, DocumentArea.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HentDokumentRequestBody }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BD.ESV.DID.DokumentUtil", name = "HentDokumentRequestBody")
    public JAXBElement<HentDokumentRequestBody> createHentDokumentRequestBody(HentDokumentRequestBody value) {
        return new JAXBElement<HentDokumentRequestBody>(_HentDokumentRequestBody_QNAME, HentDokumentRequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentAreaList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentAreaList")
    public JAXBElement<DocumentAreaList> createDocumentAreaList(DocumentAreaList value) {
        return new JAXBElement<DocumentAreaList>(_DocumentAreaList_QNAME, DocumentAreaList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "Key", scope = Parameter.class)
    public JAXBElement<String> createParameterKey(String value) {
        return new JAXBElement<String>(_ParameterKey_QNAME, String.class, Parameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "Value", scope = Parameter.class)
    public JAXBElement<String> createParameterValue(String value) {
        return new JAXBElement<String>(_ParameterValue_QNAME, String.class, Parameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "Name", scope = DocumentArea.class)
    public JAXBElement<String> createDocumentAreaName(String value) {
        return new JAXBElement<String>(_DocumentAreaName_QNAME, String.class, DocumentArea.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "Id", scope = DocumentArea.class)
    public JAXBElement<String> createDocumentAreaId(String value) {
        return new JAXBElement<String>(_DocumentAreaId_QNAME, String.class, DocumentArea.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "Document", scope = ArchivePdfDocument.class)
    public JAXBElement<byte[]> createArchivePdfDocumentDocument(byte[] value) {
        return new JAXBElement<byte[]>(_ArchivePdfDocumentDocument_QNAME, byte[].class, ArchivePdfDocument.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceNumberList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "ReferenceNumberList", scope = ArchivePdfDocument.class)
    public JAXBElement<ReferenceNumberList> createArchivePdfDocumentReferenceNumberList(ReferenceNumberList value) {
        return new JAXBElement<ReferenceNumberList>(_ReferenceNumberList_QNAME, ReferenceNumberList.class, ArchivePdfDocument.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParameterList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "ParameterList", scope = ArchivePdfDocument.class)
    public JAXBElement<ParameterList> createArchivePdfDocumentParameterList(ParameterList value) {
        return new JAXBElement<ParameterList>(_ParameterList_QNAME, ParameterList.class, ArchivePdfDocument.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "ShowCustomerCode", scope = DocumentType.class)
    public JAXBElement<String> createDocumentTypeShowCustomerCode(String value) {
        return new JAXBElement<String>(_DocumentTypeShowCustomerCode_QNAME, String.class, DocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "Message", scope = DocumentType.class)
    public JAXBElement<String> createDocumentTypeMessage(String value) {
        return new JAXBElement<String>(_DocumentTypeMessage_QNAME, String.class, DocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "PrintId", scope = DocumentType.class)
    public JAXBElement<String> createDocumentTypePrintId(String value) {
        return new JAXBElement<String>(_DocumentTypePrintId_QNAME, String.class, DocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentTypeId", scope = DocumentType.class)
    public JAXBElement<String> createDocumentTypeDocumentTypeId(String value) {
        return new JAXBElement<String>(_DocumentTypeDocumentTypeId_QNAME, String.class, DocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentTypeName", scope = DocumentType.class)
    public JAXBElement<String> createDocumentTypeDocumentTypeName(String value) {
        return new JAXBElement<String>(_DocumentTypeDocumentTypeName_QNAME, String.class, DocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FieldList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "FieldList", scope = DocumentType.class)
    public JAXBElement<FieldList> createDocumentTypeFieldList(FieldList value) {
        return new JAXBElement<FieldList>(_FieldList_QNAME, FieldList.class, DocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "AccessOnlineBankingCode", scope = DocumentType.class)
    public JAXBElement<String> createDocumentTypeAccessOnlineBankingCode(String value) {
        return new JAXBElement<String>(_DocumentTypeAccessOnlineBankingCode_QNAME, String.class, DocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentAreaId", scope = DocumentType.class)
    public JAXBElement<String> createDocumentTypeDocumentAreaId(String value) {
        return new JAXBElement<String>(_DocumentTypeDocumentAreaId_QNAME, String.class, DocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentInfoList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentInfoList", scope = ArchivedPdfDocument.class)
    public JAXBElement<DocumentInfoList> createArchivedPdfDocumentDocumentInfoList(DocumentInfoList value) {
        return new JAXBElement<DocumentInfoList>(_DocumentInfoList_QNAME, DocumentInfoList.class, ArchivedPdfDocument.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentTypeList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentTypeList", scope = DocumentTypes.class)
    public JAXBElement<DocumentTypeList> createDocumentTypesDocumentTypeList(DocumentTypeList value) {
        return new JAXBElement<DocumentTypeList>(_DocumentTypeList_QNAME, DocumentTypeList.class, DocumentTypes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentAreaList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentAreaList", scope = DocumentTypes.class)
    public JAXBElement<DocumentAreaList> createDocumentTypesDocumentAreaList(DocumentAreaList value) {
        return new JAXBElement<DocumentAreaList>(_DocumentAreaList_QNAME, DocumentAreaList.class, DocumentTypes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "TypeCodeText", scope = Field.class)
    public JAXBElement<String> createFieldTypeCodeText(String value) {
        return new JAXBElement<String>(_FieldTypeCodeText_QNAME, String.class, Field.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "ObltorCode", scope = Field.class)
    public JAXBElement<String> createFieldObltorCode(String value) {
        return new JAXBElement<String>(_FieldObltorCode_QNAME, String.class, Field.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "SortNumber", scope = Field.class)
    public JAXBElement<String> createFieldSortNumber(String value) {
        return new JAXBElement<String>(_FieldSortNumber_QNAME, String.class, Field.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "FieldName", scope = Field.class)
    public JAXBElement<String> createFieldFieldName(String value) {
        return new JAXBElement<String>(_FieldFieldName_QNAME, String.class, Field.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "PlaceCode", scope = Field.class)
    public JAXBElement<String> createFieldPlaceCode(String value) {
        return new JAXBElement<String>(_FieldPlaceCode_QNAME, String.class, Field.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BD.ESV.DID.DokumentUtil", name = "userId", scope = HentDokumentRequestBody.class)
    public JAXBElement<String> createHentDokumentRequestBodyUserId(String value) {
        return new JAXBElement<String>(_HentDokumentRequestBodyUserId_QNAME, String.class, HentDokumentRequestBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BD.ESV.DID.DokumentUtil", name = "dokId", scope = HentDokumentRequestBody.class)
    public JAXBElement<String> createHentDokumentRequestBodyDokId(String value) {
        return new JAXBElement<String>(_HentDokumentRequestBodyDokId_QNAME, String.class, HentDokumentRequestBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BD.ESV.DID.DokumentUtil", name = "bankNr", scope = HentDokumentRequestBody.class)
    public JAXBElement<String> createHentDokumentRequestBodyBankNr(String value) {
        return new JAXBElement<String>(_HentDokumentRequestBodyBankNr_QNAME, String.class, HentDokumentRequestBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BD.ESV.DID.DokumentUtil", name = "refNr", scope = HentDokumentRequestBody.class)
    public JAXBElement<String> createHentDokumentRequestBodyRefNr(String value) {
        return new JAXBElement<String>(_HentDokumentRequestBodyRefNr_QNAME, String.class, HentDokumentRequestBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArchivePdfDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "ArchivePdfDocument", scope = ArchivePdfDocumentRequest.class)
    public JAXBElement<ArchivePdfDocument> createArchivePdfDocumentRequestArchivePdfDocument(ArchivePdfDocument value) {
        return new JAXBElement<ArchivePdfDocument>(_ArchivePdfDocument_QNAME, ArchivePdfDocument.class, ArchivePdfDocumentRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "ReferenceNumber", scope = DocumentInfo.class)
    public JAXBElement<String> createDocumentInfoReferenceNumber(String value) {
        return new JAXBElement<String>(_DocumentInfoReferenceNumber_QNAME, String.class, DocumentInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://did.esv.bankdata.dk", name = "DocumentId", scope = DocumentInfo.class)
    public JAXBElement<String> createDocumentInfoDocumentId(String value) {
        return new JAXBElement<String>(_DocumentInfoDocumentId_QNAME, String.class, DocumentInfo.class, value);
    }

}
