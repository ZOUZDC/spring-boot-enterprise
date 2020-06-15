package zdc.enterprise.constants;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Random;

@Component
public class MyFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //在这里有两种返回方式, 1.转发或者OtherExceptionHandlerde /error接口 2.抛异常然后通过OtherExceptionHandler返回 3,就是直接在response中写返回值,在前后端项目中需要在此处设置跨域,也恶意单独有一个filter跨域

        Random random = new Random();
        switch (random.nextInt(4)){
            case 0:{
                request.setAttribute("javax.servlet.error.status_code",5001);
                request.setAttribute("javax.servlet.error.status_msg","通过转发/error设置返回数据");
                request.getRequestDispatcher("/error").forward(request, response);
                return;
            }
            case 1:{
                //通过此方式返回的异常数据的http状态码是500   DispatcherType.ERROR 需要处理
                throw new CustomException("通过MyFilter,抛异常返回值");
            }
            case 2:{
                System.out.println("到这里到MyFilter21");
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().print("通过MyFilter直接接调用response返回数据");
                response.getWriter().flush();
                response.getWriter().close();
                return;
            }
            default:{
                //正常通过
                chain.doFilter(request,response);
            }
        }


    }

    @Override
    public void destroy() {

    }

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(3));
        System.out.println(random.nextInt(3));
        System.out.println(random.nextInt(3));
        System.out.println(random.nextInt(3));
        System.out.println(random.nextInt(3));
        System.out.println(random.nextInt(3));
        System.out.println(random.nextInt(3));
        System.out.println(random.nextInt(3));
        System.out.println(random.nextInt(3));
        System.out.println(random.nextInt(3));
        System.out.println(random.nextInt(3));
        System.out.println(random.nextInt(3));
        System.out.println(random.nextInt(3));
        System.out.println(random.nextInt(3));

    }
}
