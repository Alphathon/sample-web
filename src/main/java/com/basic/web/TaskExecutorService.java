@Service
public class TaskExecutorService {

    @Autowired
    private LogWebSocketHandler logWebSocketHandler;

    @Async
    public String processTask(String username, String password, String option) throws InterruptedException {
        // Simulate long-running process (5-10 minutes)
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);  // Simulating 10 seconds of work
            log("Processing step " + (i + 1));
        }

        // Generate Excel File (Apache POI)
        String excelFilePath = generateExcel();
        return excelFilePath;
    }

    @Async
    public String processTask(String username, String password, String option) throws InterruptedException, IOException {
        // Simulate long-running process
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);  // Simulating 10 seconds of work
            logWebSocketHandler.sendLog("Processing step " + (i + 1));
        }

        // Generate Excel File (Apache POI)
        String excelFilePath = generateExcel();
        return excelFilePath;
    }

    private String generateExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Results");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Username");
        row.createCell(1).setCellValue("Option");

        // Write the data to the Excel file
        String fileName = "results.xlsx";
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            workbook.write(fos);
        }
        return fileName;
    }

    private void log(String message) {
        // Implement real-time logging using WebSocket or SSE (Server-Sent Events)
        System.out.println(message);  // This could be replaced with WebSocket messages
    }
}
