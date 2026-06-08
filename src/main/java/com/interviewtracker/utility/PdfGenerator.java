package com.interviewtracker.utility;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.interviewtracker.model.User;
import com.interviewtracker.model.Analytics;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PdfGenerator {
    
    public static void generateProgressReport(OutputStream outputStream, User user, Analytics analytics, int readinessScore, Map<String, Integer> weakTopics, List<String> badges) {
        try {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Page 1 - Executive Summary
            document.add(new Paragraph("Smart Interview Preparation Tracker").setBold().setFontSize(24).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Progress Report").setFontSize(18).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Generated: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("User Profile").setBold().setFontSize(16));
            document.add(new Paragraph("Name: " + user.getName()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph("College: " + (user.getCollege() != null ? user.getCollege() : "N/A")));
            document.add(new Paragraph("Branch: " + (user.getBranch() != null ? user.getBranch() : "N/A")));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Preparation Overview").setBold().setFontSize(16));
            Table table = new Table(UnitValue.createPercentArray(new float[]{50, 50})).useAllAvailableWidth();
            table.addCell(new Cell().add(new Paragraph("Readiness Score:")));
            table.addCell(new Cell().add(new Paragraph(readinessScore + "%")));
            table.addCell(new Cell().add(new Paragraph("Total Solved:")));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(analytics.getTotalQuestionsSolved()))));
            document.add(table);

            document.add(new Paragraph("\n\n\n\n"));

            // Page 2 - Analytics Summary (Simulated Page break logic can be handled via AreaBreak)
            // document.add(new AreaBreak());
            document.add(new Paragraph("Analytics Summary").setBold().setFontSize(16));
            
            Table diffTable = new Table(UnitValue.createPercentArray(new float[]{50, 50})).useAllAvailableWidth();
            diffTable.addCell(new Cell().add(new Paragraph("Easy").setBold()));
            diffTable.addCell(new Cell().add(new Paragraph(String.valueOf(analytics.getEasyCount()))));
            diffTable.addCell(new Cell().add(new Paragraph("Medium").setBold()));
            diffTable.addCell(new Cell().add(new Paragraph(String.valueOf(analytics.getMediumCount()))));
            diffTable.addCell(new Cell().add(new Paragraph("Hard").setBold()));
            diffTable.addCell(new Cell().add(new Paragraph(String.valueOf(analytics.getHardCount()))));
            document.add(diffTable);

            document.add(new Paragraph("\n"));

            // Page 4 - Achievements & Recommendations
            document.add(new Paragraph("Achievements & Recommendations").setBold().setFontSize(16));
            document.add(new Paragraph("Badges Earned:").setBold());
            for(String badge : badges) {
                document.add(new Paragraph("- " + badge));
            }
            
            document.add(new Paragraph("\nAreas for Improvement:").setBold());
            if(weakTopics != null && !weakTopics.isEmpty()) {
                weakTopics.forEach((topic, count) -> {
                    document.add(new Paragraph("- " + topic + " (Only " + count + " solved)"));
                });
            } else {
                document.add(new Paragraph("Keep up the good work!"));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
