@RestController
@RequestMapping("/execute")
public class TaskController {

    @Autowired
    private TaskExecutorService taskExecutorService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> executeTask(@RequestParam String username, @RequestParam String password, @RequestParam String dropdown) {
        Map<String, Object> response = new HashMap<>();
        try {
            String fileUrl = taskExecutorService.processTask(username, password, dropdown);
            response.put("success", true);
            response.put("downloadUrl", fileUrl);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
