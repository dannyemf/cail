HERRAMIENTAS Y TECNOLOGÍAS USADAS:
=================================================================================

- JBOSS: 7.1.0
  No usar la 7.1.1,  ya que lanza excepción al procesar los parámetros en acciones (menús) - Error: (https://community.jboss.org/thread/200993)

----------------------------------------------------------------------------------------------------

- JBOSS EL: 2.2.O
  Lenguaje de expresiones: /WEB-INF/lib/jboss-el-2.2.0.GA.jar
  Para procesar invocar métodos, ya que en la version 2.0 (defecto) no soporta: #{bena.test()}

  En el web.xml configurar:

  <context-param>
      <param-name>com.sun.faces.expressionFactory</param-name>
      <param-value>org.jboss.el.ExpressionFactoryImpl</param-value>
  </context-param>

----------------------------------------------------------------------------------------------------

- ICE FACES: 3.3.0 (+Superior)

- PLUGIN ICE FACES PARA NETBEANS: ICEfaces-3.3.0 (+Superior)

----------------------------------------------------------------------------------------------------

- NETBEANS: 7.3.1 (+Superior)

----------------------------------------------------------------------------------------------------

- MYSQL: 5 (+Superior)

----------------------------------------------------------------------------------------------------

- JASPER REPORTS: 4.6.0 (+Superior)
  JDT-COMPILER: /WEB-INF/lib/jdt-compiler-3.1.1
  Si se usa con GlassFish, se debe incluir el compiler, de lo contario lanzará excepción al compilar los jrxml.