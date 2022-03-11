package ru.brightway.HelpDeskV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brightway.HelpDeskV2.Entites.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {



}