DROP TABLE IF EXISTS user_achievements;
DROP TABLE IF EXISTS mails;
DROP TABLE IF EXISTS friends;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS achievements;

DROP TABLE IF EXISTS question_response;
DROP TABLE IF EXISTS picture_response;
DROP TABLE IF EXISTS fill_blank;
DROP TABLE IF EXISTS multiple_choice;
DROP TABLE IF EXISTS quizHistory;
DROP TABLE IF EXISTS quizzes;


CREATE TABLE accounts(
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         username VARCHAR(255) UNIQUE NOT NULL,
                         password_hash VARCHAR(255) NOT NULL
);
CREATE TABLE friends(
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        friend_id1 INT NOT NULL,
                        friend_id2 INT NOT NULL,
                        CONSTRAINT fk_friend_id1 FOREIGN KEY (friend_id1) REFERENCES accounts(id),
                        CONSTRAINT fk_friend_id2 FOREIGN KEY (friend_id2) REFERENCES accounts(id)
);
CREATE TABLE mails(
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      sender_id INT NOT NULL,
                      receiver_id INT NOT NULL,
                      mail_type VARCHAR(255) NOT NULL,
                      message VARCHAR(255) NOT NULL,
                      quiz_id INT NOT NULL DEFAULT -1,
                      date_sent DATETIME DEFAULT   CURRENT_TIMESTAMP,
                      CONSTRAINT fk_sender_id FOREIGN KEY (sender_id) REFERENCES accounts(id),
                      CONSTRAINT fk_reciever_id FOREIGN KEY (receiver_id) REFERENCES accounts(id)
);

CREATE TABLE quizzes(
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255),
                        description VARCHAR(255),
                        quiz_type VARCHAR(255),
                        num_participants_made INT,
                        quiz_creation_date DATETIME DEFAULT   CURRENT_TIMESTAMP,
                        author VARCHAR(256)
);
CREATE TABLE quizHistory(
                         quiz_name VARCHAR(256),
                         username VARCHAR(256),
                         score VARCHAR(256),
                         quiz_creation_date DATETIME DEFAULT   CURRENT_TIMESTAMP
);
CREATE TABLE question_response(
                                  quiz_id INT,
                                  question VARCHAR(255),
                                  answer VARCHAR(255),
                                  CONSTRAINT fk_qr_quiz_id FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);
CREATE TABLE picture_response(
                                 quiz_id INT,
                                 url VARCHAR(255),
                                 answer VARCHAR(255),
                                 CONSTRAINT fk_pr_quiz_id FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);
CREATE TABLE fill_blank(
                           quiz_id INT,
                           first_part VARCHAR(255),
                           second_part VARCHAR(255),
                           answer VARCHAR(255),
                           CONSTRAINT fk_fb_quiz_id FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);
CREATE TABLE multiple_choice(
                                quiz_id INT,
                                question VARCHAR(255),
                                correct_answer VARCHAR(255),
                                answer1 VARCHAR(255),
                                answer2 VARCHAR(255),
                                answer3 VARCHAR(255),
                                answer4 VARCHAR(255),
                                answer5 VARCHAR(255),
                                answer6 VARCHAR(255),
                                CONSTRAINT fk_mc_quiz_id FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);

CREATE TABLE achievements(
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             title VARCHAR(255) NOT NULL,
                             description VARCHAR(255) NOT NULL,
                             icon_url VARCHAR(255) NOT NULL
);
CREATE TABLE user_achievements(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    achievement_id INT NOT NULL,
    CONSTRAINT fk_usach_user_id FOREIGN KEY (user_id) REFERENCES accounts (id),
    CONSTRAINT fk_usach_achievement_id FOREIGN KEY (achievement_id) REFERENCES achievements(id)
);
