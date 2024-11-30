package com.webquizz.webquizz.Controler;

import com.webquizz.webquizz.Reponsitory.MakeExamRepository;
import com.webquizz.webquizz.Service.MakeExamService;
import com.webquizz.webquizz.model.ExamResultDTO;
import com.webquizz.webquizz.model.make_exam;
import com.webquizz.webquizz.model.exam;
import com.webquizz.webquizz.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MakeExamController {

    @Autowired
    private MakeExamService makeExamService;

    @Autowired
    private MakeExamRepository MakeExamRepository;




//    @PostMapping("/make-exam")
//    public String submitExamResults(
//            @RequestParam("userId") String userId,
//            @RequestParam("examId") String examId,
//            @RequestParam("result") String result, // Kết quả bài thi
//            Model model) {
//
//        // Lấy thông tin người dùng và bài thi từ cơ sở dữ liệu
////        String userId = "12345";  // Ví dụ ID người dùng
////        String examId = "67890";  // Ví dụ ID bài thi
////        String result = "85%";
//
//        // Tạo đối tượng make_exam để lưu kết quả
//        make_exam makeExam = new make_exam();
//        makeExam.setUser(userId);
//        makeExam.setExam(examId);
//        makeExam.setKetqua(result); // Lưu kết quả vào cột "ketqua"
//
//        // Lưu kết quả bài thi vào cơ sở dữ liệu
//        makeExamService.save(makeExam);
//
//        model.addAttribute("success", "Kết quả bài thi đã được lưu thành công.");
//        return "result"; // Trang kết quả sau khi lưu
//    }
@PostMapping("/make-exam")
public ResponseEntity<Map<String, Object>> submitExamResults(@RequestBody ExamResultDTO examResultDTO) {
    String userId = examResultDTO.getUserId();
    String examId = examResultDTO.getExamId();
    String result = examResultDTO.getResult();
    String userName = examResultDTO.getUserName();


    // Kiểm tra nếu đã có bản ghi với examId và userId giống nhau
    List<make_exam> existingMakeExams = MakeExamRepository.findByUserAndExam(userId, examId);

    // Nếu đã tồn tại bài kiểm tra cho userId và examId, thì cập nhật kết quả
    if (!existingMakeExams.isEmpty()) {
        make_exam existingExam = existingMakeExams.get(0);  // Lấy bản ghi đầu tiên (nếu có nhiều hơn một)
        existingExam.setKetqua(result);  // Cập nhật kết quả
        makeExamService.save(existingExam);  // Lưu bản cập nhật vào cơ sở dữ liệu

        // Trả về phản hồi thành công khi cập nhật
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Kết quả bài thi đã được cập nhật thành công.");
        return ResponseEntity.ok(response);
    } else {
        // Nếu chưa có bản ghi, tạo mới bài kiểm tra và lưu vào cơ sở dữ liệu
        make_exam makeExam = new make_exam();
        makeExam.setUser(userId);
        makeExam.setExam(examId);
        makeExam.setKetqua(result);
        makeExam.setUserName(userName);// Lưu kết quả vào cột "ketqua"

        makeExamService.save(makeExam);  // Lưu bài kiểm tra mới vào cơ sở dữ liệu

        // Trả về phản hồi thành công khi tạo mới
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Kết quả bài thi đã được lưu thành công.");
        return ResponseEntity.ok(response);
    }
}

}
