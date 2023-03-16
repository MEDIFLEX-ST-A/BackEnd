DELETE FROM member;

INSERT INTO member(member_id,created_at, updated_at, member_name, member_major, nickname, place, roleNum, role_name, user_id, user_pw, watch_time) VALUES (1, now(), now(), "손현석", null, "현토기", "서울", 1, "주임", "user6", "user6pw", 500);