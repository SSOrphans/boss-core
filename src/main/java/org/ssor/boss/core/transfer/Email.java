package org.ssor.boss.core.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Email {
    private String sender;
    private String recipient;
    private String subject;
    private String body;
}
