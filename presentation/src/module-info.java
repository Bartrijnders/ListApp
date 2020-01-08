module presentation {
    requires domein;
    requires logic;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    exports com.todolist.presentation.main;
    exports com.todolist.presentation.controllers;
    opens com.todolist.presentation.controllers;
}