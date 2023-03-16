DELETE FROM member;

INSERT INTO member(member_id, member_name, member_major, nickname, place, roleNum, role_name, user_id, user_pw, watch_time, created_at, updated_at) VALUES (1, "정형돈", "정형외과", "정형적", "서울", 0, null, "user1", "user1pw", 100, now(), now());
INSERT INTO member(member_id, member_name, member_major, nickname, place, roleNum, role_name, user_id, user_pw, watch_time, created_at, updated_at) VALUES (2, "유재석", null, "내맘임", "인천", 1, "대리", "user2", "user2pw", 80, now(), now());
INSERT INTO member(member_id, member_name, member_major, nickname, place, roleNum, role_name, user_id, user_pw, watch_time, created_at, updated_at) VALUES (3, "정준하", "치과", "식신", "대전", 0, null, "user3", "user3pw", 120, now(), now());
INSERT INTO member(member_id, member_name, member_major, nickname, place, roleNum, role_name, user_id, user_pw, watch_time, created_at, updated_at) VALUES (4, "하하", "외과", "하하하", "대구", 0, null, "user4", "user4pw", 60, now(), now());
INSERT INTO member(member_id, member_name, member_major, nickname, place, roleNum, role_name, user_id, user_pw, watch_time, created_at, updated_at) VALUES (5, "박명수", null, "늙은이", "제주", 1, "팀장", "user5", "user5pw", 200, now(), now());