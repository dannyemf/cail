
function Init(){
    try{        
        InitHandlers();
        InitEvents();
        InitPath();
        InitLinks();
        InitButtons();
        InitPanelConfirmacion();
    }catch(e){
        alert("Global.Init(): " +e );
    }
};

function InitMin(){
    try{
        InitEvents();
        InitLinks();
        InitButtons();
        InitPanelConfirmacion();
    }catch(e){
        alert("Global.InitMin(): " +e );
    }
};

function InitLinks(){
    $j('.iceCmdLnk-dis').css('opacity',0.5);
};

function InitHandlers(){
    var postUpdateHandler = function(updates) { 
        InitMin();
    };            
    ice.onAfterUpdate(postUpdateHandler);            

    var preUpdateHandler = function(updates) {
        
    };
    ice.onBeforeUpdate(preUpdateHandler);
};

function InitButtons(){    
    //menus
    
    $j('.opciones').addClass('ui-widget ui-widget-header ui-corner-all ui-state-focus');
    $j('.controles').addClass('ui-widget ui-widget-header ui-corner-all ui-state-highlight');
        
    //boton activo
    $j('.cail-button').each(function(){_InitButton(this, false);});

    //boton inactivo
    $j('.cail-button-dis').each(function(){_InitButton(this, true);});
    
    
    $j('.cail-buttonset').buttonset();
    
    $j('.cail-button-recurso-row').each(function(){_InitButtonRecursoRow(this, false);}); 
    
    $j('.ui-inputfield').removeClass('ui-state-default');
};

function InitPanelConfirmacion(){
    
    //JBoss 7.1.1 --> Pone nombre a cualquier hidden q no lo tenga para que no de error al procesar los parámetros
    $j('input[type="hidden"]').each(function(){
        var id = $j(this).attr('id');
        var name = $j(this).attr('name');        
        if(name == null || name == ''){
            $j(this).attr('name',id);
        }
    });
    
    //Reemplaza los input por button para poder aplicar los iconos
    $j('.icePnlCnfBtns').each(function(){
        var html = $j(this).html();
        html = html.replace('<input ', '<button ').replace('<input ', '<button ').replace('> ', '></button>').replace('> ', '></button>');
        $j(this).html(html);        
    });
    
    //Aplica los botones
    $j('.icePnlCnfBtns button').each(function(index){        
        $j(this).button({label:$j(this).attr('value'), icons:{primary: index % 2 == 0 ? 'ui-icon-check' : 'ui-icon-close'}});
    });
    
    //Agrega la imagen a la parte izquierda
    $j('.icePnlCnfEliminar .icePnlCnfBody').each(function(){
        $j(this).html('<img src="./resources/icono/mensaje/eliminar.png" alt="Eliminar" style="text-align: left; float: left;"/>' + $j(this).text());
    });        
};

function _InitButton(button, disabled){
    var c = _GetIcon(button);
    var title = $j(button).attr('title');
    var text = $j(button).text();    
    var big = $j(button).hasClass("cail-button-big");
    var bText = text.length == 0 ? false : true;    
    
    if(title == null || title == 'undefined' || title==''){        
        $j(button).attr('title', text);
    }
    
    $j(button).button({disabled:disabled, icons: {primary: c}, text: (bText || big ? true : false)});
    
    if(bText == false && big == false){
        $j(button).css('width','16px');
        $j(button).css('height','16px');
    }
};

function _InitButtonRecursoRow(button){
    $j(button).hover(function(){
        $j(this).addClass('ui-state-hover');
    }, function(){
        $j(this).removeClass('ui-state-hover');
    });
    
    var text = $j(button).text();
    var title = text;
    var image = './resources/icono/row/row_reporte.png';
    
    try{
        var json = eval("["+text+"]")[0];
        text = json.text;
        title = json.title;
        image = json.image;
    }catch(e){        
        //alert('InitButtonRecursoRow('+text+'): '+e);
    }        
    
    $j(button).html('<img src="' + image + '"/><span> ' + text + '</span>');        
    $j(button).attr('title',title);
}


function _GetIcon(button){
    try{
        var clases = $j(button).attr('class');
        if(clases != 'undefined'){
            var spc = clases.split(' ');                        
            for(var s = 0; s != spc.length; s++ ){
                var c = spc[s];
                if(c.indexOf("ui-icon-") >= 0){
                    return c;
                }                            
            }
        }
    }catch(e){
        alert(e);
    }
};

function InitEvents(){
    try{
        $j('.numeric').numeric(); //Sólo numeros
        $j('.integer').numeric(); //Sólo enteros
        $j('.decimal').numeric({allow:'.'}); //Solo decimales
        $j('.phone').numeric({allow:'-'}); //Números teléfonos
        
        $j('.name').alpha({allow:' '}); //Nombres propios
        $j('.desc').alphanumeric({allow:' (),.;-_'}); //Descripciones, Observaciones, etc.
        $j('.login').alpha({nocaps:true}); //Nombre de usuario
        $j('.lower-code').alpha({nocaps:true, allow:'_'}); //Código en minúsculas: code_code
        $j('.lower-code-punto').alpha({nocaps:true, allow:'.'}); //Códigos en minúscula con punto: code.code
        $j('.upper-code').alpha({allcaps:true, allow:'_'}); //Código en mayúscula: CODE_CODE
    }catch(e){
        alert(e);
    }
};

function InitPath(){
    var sp = $j('#subpath').val();
    if(sp){
        $j('#path').text($j('#path').text() + " / " + sp);
    }
};

/**
 * Verifica y si la entrada tiene caracteres no perteneciente al rango [0-9] (Enteros),
 * elimnando los otros caracteres. Se lo llama desde onblur y onchange
 * {HTMLElement} control
 * {KeyEvent} event
 */
function InputIntVerify(control, event){
    var v = control.value;
    var vf = "";
    for(var i = 0; i < v.length; i++){
        if(isNaN(v[i]) == false){
            vf += v[i];
        }
    }
    control.value = vf;    
};

/**
 * Evneto onkeypress para evitar ingresar caracteres que no sean números.
 * {HTMLElement} control
 * {KeyEvent} event
 */
function InputInt(control, event){
    var k = event.charCode;
    var allow = false;
    
    if((k >= 48 && k <= 57) || k==0){
        allow = true;
    }
    
    if(!allow){
        event.preventDefault();
        event.preventCapture();
    }
};

