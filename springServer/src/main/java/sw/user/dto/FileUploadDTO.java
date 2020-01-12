package sw.user.dto;

public class FileUploadDTO {

    private String fileName;
    private byte[] fileContent;

    public FileUploadDTO() {
    }

    public FileUploadDTO(String fileName, byte[] fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public FileUploadDTO setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public FileUploadDTO setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
        return this;
    }
}
