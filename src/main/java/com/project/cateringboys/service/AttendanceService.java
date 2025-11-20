package com.project.cateringboys.service;

import com.project.cateringboys.model.Attendance;
import com.project.cateringboys.model.enums.AttendanceStatus;

public interface AttendanceService {
      void markAttendance(Long BookingId,AttendanceStatus attendanceStatus,String attendedAt);
      Attendance getAttendanceByBooking(Long BookingId);
}
