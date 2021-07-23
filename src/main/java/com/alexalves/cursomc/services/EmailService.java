package com.alexalves.cursomc.services;


import org.springframework.mail.SimpleMailMessage;

import com.alexalves.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
}