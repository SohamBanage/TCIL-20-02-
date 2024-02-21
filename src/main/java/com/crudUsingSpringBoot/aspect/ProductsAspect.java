package com.crudUsingSpringBoot.aspect;

import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.crudUsingSpringBoot.Entity.Products;

@Aspect
@Component
public class ProductsAspect {

	//Before Advice
	@Before(value ="execution(* com.crudUsingSpringBoot.controller.ProductController.saveProducts(..)) && args(products)")
	public void beforeCreatingProduct(JoinPoint joinPoint,Products products) {
		System.out.println("Before executing method ::"+joinPoint.getSignature());
		System.out.println("The Creating the product with name:- "+products.getName()+" and the price:- "+products.getPrice());
	}
	
	
	//After Advice
	@After(value ="execution(* com.crudUsingSpringBoot.controller.ProductController.deleteProduct(..))")
	public void afterDeletingProduct(JoinPoint joinPoint) {
		System.out.println("After executing method ::"+joinPoint.getSignature());
		System.out.println("Employee Deleted Properly");
	}
	
	//Around Advice for Update Products
	@Around(value="execution(* com.crudUsingSpringBoot.controller.ProductController.updateProducts(..)) && args(id,products)")
	public Object aroundEditProduct(ProceedingJoinPoint proceedingJoinPoint,long id,Products products) throws Throwable {
		System.out.println("Before Updatig method "+proceedingJoinPoint.getSignature());
		System.out.println("Id is:- "+id);
		Object result = proceedingJoinPoint.proceed();
		System.out.println("After updating method "+proceedingJoinPoint.getSignature());
		System.out.println("Id is:- "+id);
		return result;
		
	}
//	
//	@Pointcut(value= "execution(* com.crudUsingSpringBoot.controller.ProductController.*(..))")
//	private void productControllerLog( ) {
//		
//	}
//	
//	@Around(value= "productControllerLog()")  
//	public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable   
//		{  
//			System.out.println("The method aroundAdvice() before invokation of the method " + proceedingJoinPoint.getSignature().getName() + " method");  
//			try   
//			{  
//				proceedingJoinPoint.proceed();  
//			}   
//			finally   
//			{  
//	  
//			}  
//			System.out.println("The method aroundAdvice() after invokation of the method " + proceedingJoinPoint.getSignature().getName() + " method");  
//			
//		}  
	
	
	//Around Advice

//	@Pointcut("execution(* com.crudUsingSpringBoot.controller.ProductController.*(..))")
//    private void productControllerLog() {
//    }
//
//    @Around("productControllerLog()")
//    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable  {
//        System.out.println("Before method execution :: "+joinPoint.getSignature());
//        Object object = joinPoint.proceed();
//        
//        if(object instanceof Products) {
//        	System.out.println("After method execution :: "+joinPoint.getSignature());
//        }
//        else  {
//			System.out.println("Noting for execution");
//		}
//        return object;
//    }
	//Around advice
//    @Around(value = "execution(* com.crudUsingSpringBoot.controller.ProductController.*(..))")
//    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("Before method execution:: " + joinPoint.getSignature());
//        Object result = joinPoint.proceed();
//        System.out.println("After method execution:: " + joinPoint.getSignature());
//        return result;
//    }
//    // After returning advice
//    @AfterReturning(value = "execution(* com.crudUsingSpringBoot.controller.ProductController.*(..))",returning = "result")
//    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
//        System.out.println("After returning from method:: " + joinPoint.getSignature());
//    }
	
    // After throwing advice
    @AfterThrowing(value = "execution(* com.crudUsingSpringBoot.controller.ProductController.*(..))",throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        System.out.println("After throwing from method:: " + joinPoint.getSignature());
        System.out.println("Exception thrown:: " + ex.getMessage());
    }
}
