# store1
用jsp servlet 写的网上商城项目 三层架构 Servlet   Service    Dao
案例1-用户注册	
需求:
	在register.jsp上填写用户的信息,点击保存,将用户信息保存到数据库中
步骤分析:
	1.设置默认首页(index.jsp),让其自动的跳转到/jsp/index.jsp
	2.修改index.jsp上的 注册 连接
		<a href='/store/user?method=registUI'>注册</a>
	3.在userservlet中编写reigstUI方法
		请求转发到 /jsp/register.jsp
	4.修改register.jsp上的表单信息
		action	="/store/user?method=regist"
		method
		为每一个子标签添加name属性
	5.点击注册按钮 向userservlet发送请求
	6.userservlet中编写regist方法
		获取参数,封装成user对象
		调用service完成注册
		生成提示信息,转发 /jsp/msg.jsp
	7.userservice中的操作:
		调用dao完成注册操作
		发送邮件
	8.userdao...
///////////////////////////////	

案例2-用户登录
需求:
	在一个登录页面上,输入用户名和密码,点击登录,完成登录操作
步骤分析:
	1.在index.jsp点击 登录 连接,跳转到登录页面
	2.在userserlvet中编写 loginUI
	3.修改login.jsp表单的信息
		action: /user?method=login
		method
		给子标签添加name属性
	5.点击提交发送请求
	6.在userservlet中编写login方法
		获取用户名和密码
		调用service完成登录 返回值:user
		若登录成功,跳转到index.jsp 并展示用户信息
		若登录失败,
			若user为空:提示 用户名和密码 跳转到login.jsp
			若user不为空但是未激活:提示信息 "请先去邮箱激活,再登录" 跳转msg.jsp
	7.service,dao
///////////////////////////////////
///////////////////////////////////
案例3-用户退出	
需求:
	点击 index.jsp上 退出连接,退出当前的用户,跳转index.jsp
步骤分析:
	1.点击 index.jsp上 退出 连接,向userservlet发送请求
		/user?methode=logout
	2.在userservlet中编写logout方法
		销毁session
		重定向到index.jsp
		
		
扩展:
	记住用户名
	需求:
		登录成功之后,若勾选了记住用户名,下一次再登录的时候,会展示出来用户名
	步骤分析:
		修改login方法的逻辑
			登录成功之后,判断是否勾选了记住用户名
				若勾选了,将用户名(将用户名编码)保存到cookie中
			
		在login.jsp加载成功的时候需要从cookie中获取用户名且展示出来
	


baseservlet的抽取:
	统一处理请求的方法
		request.getParameter("method")
		通过反射获取方法对象
		让方法执行
	统一处理请求转发
		规定所有的方法都有返回值:string
		若需要转发,返回转发路径
		若不需要转发,返回null
	统一处理错误页面 400.jdp  500.jsp
	设置默认执行方法(index)


案例1-前台分类信息展示
需求:
	访问任意页面的时候,都需要将分类的信息展示出来
技术分析:
	包含(静态包含和动态包含)
	ajax
步骤分析:
	1.创建分类表
		CREATE TABLE `category` 
		
	2.抽取所有页面上 logo和菜单部分(head.jsp)
		页面加载的时候
		编写函数
			发送ajax请求 $.post(url,params,fn,type);
				url:/store/category
				params: method=findAll
				fn:将返回值遍历,每一个分类封装成li标签,插入到ul标签内部
				type:json
			编写categoryservlet,继承baseservlet,编写findAll方法
				调用service,查询所有的分类,分类列表的json字符串(String)
				写回页面
			categoryservice中的操作
				调用dao,获取所有的分类
				将list转成json返回
	3.在所有的页面里将 head.jsp 包含进去
		获取返回值
		遍历返回值
		每一个分类封装成li标签,插入到ul标签内部


案例2-最新商品和热门商品展示
需求:
	访问首页的时候,需要将最新商品和热门商品展示出来.
技术:
	方式1:ajax异步
	方式2:同步
使用同步步骤分析(请求转发)
	0.创建商品表
		CREATE TABLE `product` 

1.访问项目首页,请求转发indexservlet
		indexservlet中使用默认index处理
			调用productservice查询热门商品和最新商品, 每一个都返回一个list
			将两个list放入request域中,请求转发到 /jsp/index.jsp
	2.在页面上将数据遍历出来
案例3-单个商品详情
需求:
	在首页上点击每个商品,将这个商品的详细信息展示在页面上(product_info.jsp)
步骤分析:
	1.给每个商品添加超链接
		<a href="/store/product?method=getById&pid=xxx">yy</a>
	2.编写productservlet,继承baseservlet,编写getById
		获取商品的pid
		调用service获取一个商品 返回值:product
		请求转发到product_info.jsp
	3.service ,dao
	4.在product_info.jsp将商品展示
案例4-分类商品的分页展示
需求:
	点击菜单栏上某一个分类的时候,将该分类下的商品,分页展示出来(默认第一页)
技术分析:
	分页
		页面上需要的数据
			当前页数据
			当前页
			总页数
			总记录数
			每页显示的条数
	limit m,n
		limit (当前页-1)*每页显示的条数,每页显示的条数
步骤分析:
	1.修改head.jsp上的每个分类的超链接
		<a href="/store/product?method=findByPage&pageNumber=1&cid=xxx">
	2.在cateservlet中编写findByPage方法
		获取pagenumber
		获取cid
		设置pageSize
		
		调用service获取分页的数据 返回值:PageBean
		
		将pagebean放入request域中,请求转发 /jsp/product_list.jsp
	3.编写service:
		返回值:pagebean
		创建一个pagebean
		
		设置当前页需要的数据
			调用dao
			
		设置总记录数
			调用dao
	4.dao
	5.在jsp/product_list.jsp上展示商品





案例1-添加到购物车
需求:
	在商品的详情页面,输入购买数量,点击加入购物车,将该商品添加到购物车了
技术分析:
	session
涉及到实体:
	购物车(cart)
		属性:
			购物项(商品购买信息)的集合
				Map<String pid,CartItem>
			总金额(total)
		方法:
			加入购物车
				add2cart(CartItem item){
					//1.判断购物车是否有该商品
					//有:	
							修改购买数量(原来的数量+item.getCount)
							修改总金额(原来的金额+item.getSubtotal())
					//无:直接put进去 修改总金额(原来的金额+item.getSubtotal())
				}
			从购物车中移除
				removeFromCart(String pid){
					从map中移除指定购物项
					修改总金额
				}
			清空购物车
				clearCart(){
					清空map
					修改总金额=0.0
				}
		/////////////////////////////////////////////
		/////////////////////////////////////////////
	购物项(cartItem)
		属性:
			商品对象(product)
			购买数量(count)
			小计(subtotal)
		方法:
			重新编写 
			getSubtotal(){
				return product.getShop_price*count
			}
步骤分析:
	1.修改商品详情页面(product_info.jsp)
		添加一个表单:
			要执行方法:method=add2cart
			商品的pid:pid=xxx
			商品的购买数量:count=123123
		点击"加入购物车",将表单提交 /store/cart
	2.编写cartServlet 继承baseservlet 编写add2cart
		获取商品的pid和count
		调用productservice通过pid获取商品
		
		封装cartItem
			new cartItem(Product,count)
		将cartitem加入购物车
			获取购物车(session中获取)
			调用购物车的add2cart方法即可
			
		重定向到cart.jsp上
////////////////////////////////
////////////////////////////////
案例2-从购物车移除一个商品
需求:
	在cart.jsp上,点击某一个商品的 删除 ,弹出一个提示"您忍心抛弃我吗?",点击确定,从购物车中移除.否则不删
步骤分析:
	1.给 删除 添加连接
		/store/cart?method=remove&pid=xxx
	2.在cartservlet中编写remove方法
		获取pid
		获取cart,执行removeFromCart()方法
		重定向到cart.jsp

案例3-清空购物车
需求:
	点击cart.jsp上的 清空购物车,需要将购物车中所有商品移除掉
步骤分析:
	1.修改 清空购物车的连接
		/store/cart?method=clear
	2.编写clear方法
		获取购物车,执行clearCart()
		重定向到cart.jsp上
	3.判断购物车中是否有商品,	
		有则展示
		无则提示
	
