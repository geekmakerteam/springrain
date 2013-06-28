项目名为9iuspring,因git@osc不支持数字开头的项目名称,所以用了 spring9iu,我的个人博客是 9iu.org,项目因此得名
#文档
http://git.oschina.net/chunanyong/spring9iu/raw/master/9iuspring/doc/9iuspring.pdf
#代码生成器
http://git.oschina.net/chunanyong/spring9iu/tree/master/9iuspring/gencode
#sql脚本
http://git.oschina.net/chunanyong/spring9iu/tree/master/9iuspring/sql


9iuspring是spring的极简封装,spring一站式开发的范例,spring的强大灵活+jfinal的开发效率

9iuspring本身就是一个完整的eclipse项目,spring一站式开发的范例,包含spring core,spring jdbc,spring mvc.可以认为就是一个spring的demo.

9iuspring 自带一个代码生成器,能够生成对表的增删改查的逻辑代码,以及前台页面样式和js文件

spring良好的扩展性,集成度,IOC,AOP事务,已经是项目的基础条件.

项目只依赖spring,没有hibernate,struts,ibatis.

理念和jfinal一致,提高开发效率.

hibernate太过复杂,运用不好就会造成很大影响,ibatis的优势就是把sql写入xml文件,利于数据库调优和sql语句管理.数据库调优可以使用druid直接输出比较慢的sql,比分析xml中的语句更直观,关于sql管理,9iuspring所有的sql语句都使用Finder封装,只要查看Finder在项目中的引用,就能查看项目中所有的sql语句,就此,ibatis的优势就很小了.

struts 和 spring mvc 相比,个人感觉还是有点差距的.

一些测试案例：

```java
//就极简而言,一个数据库只需要一个Service,就可以管理这个数据库的任意一张表 
//@Test  查询基本类型
public void testObject() throws Exception{
        Finder finder=new Finder("select id from [Users] where 1=1 ");
        finder.append("and id=:userId").setParam("userId", "admin");
String id = baseTestdb1Service.queryForObject(finder, String.class);
System.out.println(id);

}

//@Test 查询一个对象
public void testObjectUser() throws Exception{
        Finder finder=new Finder("select * from Users where id=:userId order by id");
        finder.setParam("userId", "admin");
        Users u = baseTestdb1Service.queryForObject(finder, Users.class);
    System.out.println(u.getName());

}
//@Test 查询分页
public void testMsSql() throws Exception{
        Finder finder=new Finder("select * from Users order by id");
List<Users> list = baseTestdb1Service.queryForList(finder, Users.class, new Page(2));
System.out.println(list.size());
 for(Users s:list){
         System.out.println(s.getName());
 }
}



//@Test 调用数据库存储过程
public void testProc() throws Exception{
        Finder finder=new Finder();
        finder.setParam("unitId", 0);
        finder.setProcName("proc_up");
        Map queryObjectByProc = (Map) baseTestdb1Service.queryObjectByProc(finder);
        System.out.println(queryObjectByProc.get("#update-count-10"));
        

}

//@Test 调用数据库函数
public void testFunction() throws Exception{
        Finder finder=new Finder();
        
        finder.setFunName("fun_userId");
        finder.setParam("userId", 6);
        String userName= baseTestdb1Service.queryForObjectByByFunction(finder,String.class);
        System.out.println(userName);
        

}

```