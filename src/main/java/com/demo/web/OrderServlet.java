package com.demo.web;

import com.demo.pojo.Cart;
import com.demo.pojo.User;
import com.demo.service.OrderService;
import com.demo.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = WebUtils.getBean(OrderService.class);
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
       User loginUser = (User)req.getSession().getAttribute("user");
       if(loginUser==null){
           req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
           return;
       }
        Integer userId = loginUser.getId();

        String orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId",orderId);
        //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
