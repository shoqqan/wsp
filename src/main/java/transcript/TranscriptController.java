package transcript;

import java.sql.SQLException;

public class TranscriptController {

    private final TranscriptService transcriptService;
    private final TranscriptView transcriptView;

    public TranscriptController(TranscriptService transcriptService, TranscriptView transcriptView) {
        this.transcriptService = transcriptService;
        this.transcriptView = transcriptView;
    }

    public void showTranscript(String studentId) throws SQLException {
        transcriptView.displayTranscript(studentId);
    }
}
