package spring.starter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
//Ini annotation buat nandain kalo itu aspect
@Aspect
public class LoggingAspect {

    /**Membuat pointcut sebagai signature nya
     * Jika return nya mau apa saja bisa menggunakan tanda *
     * Jika ingin mengubah joint point untuk semua method yang ada dalam suatu kelas bisa diubah menjadi *
     * */
    @Pointcut(value = "execution(* spring.starter.controller.*.*(..))")
    private void restApi(){}
    @Pointcut(value = "within(spring.starter.controller.*)")
    private void withinPointcut(){}

    //menggunakan args value harus dikombinasikan dengan value lain dan dihubungkan dengan &&
    @Pointcut(value = "args(spring.starter.dto.publisher.PublisherAddRequest)")
    private void argsPointcut(){}

    @Pointcut(value = "@args(spring.starter.annotation.LogThisArgs)")
    private void argsAnnotationPointcut(){}

    @Before(value = "restApi() && argsPointcut()")
    public void beforeExecutedLogging(){
        log.info("This is log from aspect before");

    }

    @After(value = "restApi() && argsPointcut()")
    public void afterExecutedLogging(){
        log.info("This is log from aspect after");

    }

    @AfterReturning(value = "restApi() && argsPointcut()")
    public void afterReturningExecutedLogging(){
        log.info("This is log from aspect after returning");

    }

    @AfterThrowing(value = "restApi() && argsPointcut()")
    public void afterThrowingExecutedLogging(){
        log.info("This is log from aspect after throwing");

    }

    /**
     * Untuk advice around bisa dibuat dengan cara dibawah
     * */
    @Around(value = "restApi()")
    public Object aroundExecutedLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        try {
            log.info("Start {}.{}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
            stopWatch.start();

            //Ini adalah pemisah antara after dan before
            return joinPoint.proceed();
        }finally {
            stopWatch.stop();
            log.info("Finish {}.{} in time = {} milisecond", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(),
                    stopWatch.getTotalTimeMillis());
        }
    }

}
