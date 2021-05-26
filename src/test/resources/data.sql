INSERT INTO boss.user (type_id, branch_id, username, email, password, created, deleted, enabled, locked)
VALUES (2, 1, 'SoraKatadzuma', 'sorakatadzuma@gmail.com',
        'D8A4D80f7C27F25067E56671B1AA4f7172E3C7418DE442fDF42fF49CF49FE20E', 0, NULL, TRUE, FALSE);

INSERT INTO boss.user (type_id, branch_id, username, email, password, created, deleted, enabled, locked)
VALUES (2, 1, 'Monkey', 'me@example.com', 'D8A4D80f7C27F25067E56671B1AA4f7172E3C7418DE442fDF42fF49CF49FE20E',
        0, NULL, TRUE, FALSE);

INSERT INTO boss.loan (loan_number, type_id, user_id, branch_id, amount, interest_rate, taken_at, due_by, amount_due)
VALUES ('1234567890', 1, 1, 1, 50000, 0.02, NOW(), NOW(), 25000);

INSERT INTO boss.loan (loan_number, type_id, user_id, branch_id, amount, interest_rate, taken_at, due_by, amount_due)
VALUES ('2345678901', 1, 1, 1, 25000, 0.1, NOW(), NOW(), 5000);

INSERT INTO boss.card (type_id, number_hash, account_id, created, active_since, expiration_date, pin, cvv, confirmed,
                       active, stolen)
VALUES (0, '1234567890123456', 1, NOW(), NOW(), NOW(), 1111, 111, TRUE, TRUE, FALSE);

INSERT INTO boss.account_holder(user_id, full_name, dob, ssn, address, city, state, zip, phone)
VALUES (1, 'Trevor Philips', 20130917, '123-45-6789', '16703 Nicklaus Dr', 'Los Anglees', 'CA', 91342, '+12735550136');
