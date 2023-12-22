//package mvc.controller;
//
//import org.apache.el.stream.Optional;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.concurrent.CopyOnWriteArrayList;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Collectors;
//
///**
// * 會議室預訂系統
// * 假設您正在為一家公司開發一個會議室預訂系統。您需要實現一個控制器，該控制器可以處理會議室的預訂請求、取消請求以及查詢當前預訂狀態。
// * 
// * 功能要求：
// * -----------------------------------------------------------------------------------------------
// * 1.預訂會議室：
// * 路徑：/booking/bookRoom
// * 參數：會議室ID (roomId), 使用者名稱 (name), 預訂日期 (date)
// * 返回：預訂成功(會得到預約號碼)或失敗的消息
// * -----------------------------------------------------------------------------------------------
// * 範例：http://localhost:8080/SpringMVC/mvc/booking/bookRoom?roomId=101&name=Tom&date=2023-12-04
// * 範例：http://localhost:8080/SpringMVC/mvc/booking/bookRoom?roomId=102&name=Mary&date=2023-12-05
// * 範例：http://localhost:8080/SpringMVC/mvc/booking/bookRoom?roomId=201&name=Jack&date=2023-12-06
// * 範例：http://localhost:8080/SpringMVC/mvc/booking/bookRoom?roomId=403&name=Rose&date=2023-12-06
// * 
// * -----------------------------------------------------------------------------------------------
// * 2.取消預訂：
// * 路徑：/booking/cancelBooking/{bookingId}
// * 參數：預訂ID (bookingId)
// * 返回：取消成功或失敗的消息
// * -----------------------------------------------------------------------------------------------
// * 範例：http://localhost:8080/SpringMVC/mvc/booking/cancelBooking/1
// * 
// * -----------------------------------------------------------------------------------------------
// * 3.查看所有預訂：
// * 路徑：/booking/viewBookings
// * 返回：當前所有預訂的列表（可以簡單地返回字符串格式的預訂詳情）
// * -----------------------------------------------------------------------------------------------
// * 範例：http://localhost:8080/SpringMVC/mvc/booking/viewBookings
// * 
// * CR
// * 4.修改預約人
// * 路徑：/booking/{bookingId}/updateName
// * 返回：是否變更成功
// * -----------------------------------------------------------------------------------------------
// * 範例：http://localhost:8080/SpringMVC/mvc/booking/1/updateName?name=Tom
// * 範例：http://localhost:8080/SpringMVC/mvc/booking/2/updateName?name=Helen
// * 
// * */
//
///**
// * 預約紀錄
// +-----------+--------+-------+------------+
// | bookingId | roomId | name  |    date    |
// +-----------+--------+-------+------------+
// |     1     |  101   |  Tom  | 2023-12-04 |
// |     2     |  102   |  Mary | 2023-12-05 |
// +-----------+--------+-------+------------+
//*/
//
//
//
//@Controller
//@RequestMapping("/booking")
//public class BookingController2 {
//    // 用于存储预订记录的列表
//    private static List<Map<String, Object>> bookings = new CopyOnWriteArrayList<>();
//    
//    // 用于生成预订ID的计数器
//    private AtomicInteger bookingIdCount = new AtomicInteger(0);
//
//    // 处理预订会议室的请求
//    @RequestMapping(value = "/bookRoom", produces = "text/plain;charset=utf-8", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public String bookRoom(
//            @RequestParam("roomId") String roomId,
//            @RequestParam("name") String name,
//            @RequestParam("date") String date) {
//        // 生成唯一的预订ID
//        int bookingId = bookingIdCount.incrementAndGet();
//        
//        // 创建预订记录
//        Map<String, Object> booking = Map.of(
//                "bookingId", bookingId,
//                "roomId", roomId,
//                "name", name,
//                "date", date
//        );
//        
//        // 将预订记录添加到列表中
//        bookings.add(booking);
//        
//        // 返回预订成功消息，并包含预订ID
//        return "預訂成功，預約號碼：" + bookingId;
//    }
//    
//    
//
//    // 处理取消预订的请求
//    @GetMapping(value = "/cancelBooking/{bookingId}", produces = "text/plain;charset=utf-8")
//    @ResponseBody
//    public String cancelBooking(@PathVariable("bookingId") int bookingId) {
//        // 找到所有符合条件的预订记录
//        List<Map<String, Object>> bookingsToRemove = bookings.stream()
//                .filter(booking -> Objects.equals(booking.get("bookingId"), bookingId))
//                .collect(Collectors.toList());
//
//        // 如果找到，则移除这些预订记录
//        if (!bookingsToRemove.isEmpty()) {
//            bookings.removeAll(bookingsToRemove);
//            return "取消成功";
//        } else {
//            return "找不到預訂ID：" + bookingId;
//        }
//    }
//    
//    //自動取消第一筆
//    @GetMapping(value = "/autoCancelFirstBooking", produces = "text/plain;charset=utf-8")
//	@ResponseBody
//	public String autoCancelFirstBooking() {
//		if(!bookings.isEmpty()) {
//			bookings.remove(0);
//			return "自動取消第一筆成功";
//		}
//		return "自動取消第一筆失敗";
//	}
//    
//
//    // 处理查看所有预订记录的请求
////    @GetMapping(value = "/viewBookings", produces = "text/html;charset=utf-8")
////    @ResponseBody 
////    public String viewBookings() {
////        // 将预订记录格式化成HTML字符串并返回
////        return bookings.stream()
////                .map(this::formatBooking)
////                .collect(Collectors.joining());
////    }
////
////    private String formatBooking(Map<String, Object> booking) {
////        return String.format(
////            "預約ID：%s，會議室：%s，預訂人：%s，日期：%s"
////            + "<button onclick=\"window.location.href='./cancelBooking/%s'\">取消預定</button><br>",
////            booking.get("bookingId"),
////            booking.get("roomId"),
////            booking.get("name"),
////            booking.get("date"),
////            booking.get("bookingId")
////        );
////    }
//    @GetMapping(value = "/viewBookings", produces = "text/plain;charset=utf-8")
//	public ModelAndView bookingViewBookings() {
//		ModelAndView mv = new ModelAndView();
//		// jsp + model 資料配置好
//		mv.addObject("bookings", bookings);
//		mv.setViewName("/WEB-INF/views/booking/list.jsp");
//		return mv;
////"<a href=\"./cancelBooking/"+booking.get("bookingId")+"\">\n"
////+ "					./mvc/booking/cancelBooking/"+booking.get("bookingId")+"\n"
////+ "				</a>",
//	}
//    
//    
//    
//}