package com.mediflix.backend.aop;

import com.mediflix.backend.service.MemberService;
import com.mediflix.backend.utils.SessionUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Component
@Aspect
@Log4j2
@SuppressWarnings("unchecked")
public class LoginCheckAspect {
    @Autowired
    private MemberService memberService;

    @Before("@annotation(com.mediflix.backend.aop.LoginCheck)")public void memberLoginCheck(JoinPoint jp) throws Throwable {
        log.debug("AOP - Member Login Check Started");

        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String memberId = SessionUtil.getLoginMemberId(session);

        if (memberId == null) {
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "NO_LOGIN") {};
        }
    }
}