package com.demo.web;

import com.demo.pojo.Book;
import com.demo.pojo.Cart;
import com.demo.pojo.CartItem;
import com.demo.service.BookService;
import com.demo.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    private BookService bookService = WebUtils.getBean(BookService.class);

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = WebUtils.parseInt(req.getParameter("id"));
        int count = WebUtils.parseInt(req.getParameter("count"),1);

        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart!=null) {
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 删除
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"));
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.delItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"));
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            cart= new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //System.out.println(cart);
        resp.sendRedirect(req.getHeader("Referer"));
        req.getSession().setAttribute("last",cartItem.getName());
    }
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"));
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            cart= new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //System.out.println(cart);
        req.getSession().setAttribute("last",cartItem.getName());
        Map <String,Object> resultMap =new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        Gson gson = new Gson();
        String resultMapJson = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJson);
    }

}
