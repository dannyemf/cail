/*
 * jQuery Nivo Slider v2.3
 * http://nivo.dev7studios.com
 *
 * Copyright 2010, Gilbert Pellegrom
 * Free to use and abuse under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 */

(function($){
    var a=function(j,q){var g=$.extend({},$.fn.nivoSlider.defaults,q);
var m={currentSlide:0,currentImage:"",totalSlides:0,randAnim:"",running:false,paused:false,stop:false}; 
var d=$(j); 
d.data("nivo:vars",m); 
d.css("position","relative"); 
d.addClass("nivoSlider"); 
var e=d.children(); 
e.each(function(){var t=$(this); 
var s=""; 
if(!t.is("img")){if(t.is("a")){t.addClass("nivo-imageLink"); 
s=t}t=t.find("img:first")}var r=t.width(); 
if(r==0){r=t.attr("width")}var i=t.height(); 
if(i==0){i=t.attr("height")}if(r>d.width()){d.width(r)}if(i>d.height()){d.height(i)}if(s!=""){s.css("display","none")}t.css("display","none"); 
m.totalSlides++}); 
if(g.startSlide>0){if(g.startSlide>=m.totalSlides){g.startSlide=m.totalSlides-1}m.currentSlide=g.startSlide}if($(e[m.currentSlide]).is("img")){m.currentImage=$(e[m.currentSlide])}else{m.currentImage=$(e[m.currentSlide]).find("img:first")}if($(e[m.currentSlide]).is("a")){$(e[m.currentSlide]).css("display","block")}d.css("background","url("+m.currentImage.attr("src")+") no-repeat"); 
for(var k=0; 
k<g.slices; 
k++){var o=Math.round(d.width()/g.slices); 
if(k==g.slices-1){d.append($('<div class="nivo-slice"></div>').css({left:(o*k)+"px",width:(d.width()-(o*k))+"px"}))}else{d.append($('<div class="nivo-slice"></div>').css({left:(o*k)+"px",width:o+"px"}))}}d.append($('<div class="nivo-caption"><p></p></div>').css({display:"none",opacity:g.captionOpacity})); 
if(m.currentImage.attr("title")!=""){var n=m.currentImage.attr("title"); 
if(n.substr(0,1)=="#"){n=$(n).html()}$(".nivo-caption p",d).html(n); 
$(".nivo-caption",d).fadeIn(g.animSpeed)}var c=0; 
if(!g.manualAdvance&&e.length>1){c=setInterval(function(){p(d,e,g,false)},g.pauseTime)}if(g.directionNav){d.append('<div class="nivo-directionNav"><a class="nivo-prevNav">Prev</a><a class="nivo-nextNav">Next</a></div>'); 
if(g.directionNavHide){$(".nivo-directionNav",d).hide(); 
d.hover(function(){$(".nivo-directionNav",d).show()},function(){$(".nivo-directionNav",d).hide()})}$("a.nivo-prevNav",d).live("click",function(){if(m.running){return false}clearInterval(c); 
c=""; 
m.currentSlide-=2; 
p(d,e,g,"prev")}); 
$("a.nivo-nextNav",d).live("click",function(){if(m.running){return false}clearInterval(c); 
c=""; 
p(d,e,g,"next")})}if(g.controlNav){var l=$('<div class="nivo-controlNav"></div>'); 
d.append(l); 
for(var k=0; 
k<e.length; 
k++){if(g.controlNavThumbs){var f=e.eq(k); 
if(!f.is("img")){f=f.find("img:first")}if(g.controlNavThumbsFromRel){l.append('<a class="nivo-control" rel="'+k+'"><img src="'+f.attr("rel")+'" alt="" /></a>')}else{l.append('<a class="nivo-control" rel="'+k+'"><img src="'+f.attr("src").replace(g.controlNavThumbsSearch,g.controlNavThumbsReplace)+'" alt="" /></a>')}}else{l.append('<a class="nivo-control" rel="'+k+'">'+(k+1)+"</a>")}}$(".nivo-controlNav a:eq("+m.currentSlide+")",d).addClass("active"); 
$(".nivo-controlNav a",d).live("click",function(){if(m.running){return false}if($(this).hasClass("active")){return false}clearInterval(c); 
c=""; 
d.css("background","url("+m.currentImage.attr("src")+") no-repeat"); 
m.currentSlide=$(this).attr("rel")-1; 
p(d,e,g,"control")})}if(g.keyboardNav){$(window).keypress(function(i){if(i.keyCode=="37"){if(m.running){return false}clearInterval(c); 
c=""; 
m.currentSlide-=2; 
p(d,e,g,"prev")}if(i.keyCode=="39"){if(m.running){return false}clearInterval(c); 
c=""; 
p(d,e,g,"next")}})}if(g.pauseOnHover){d.hover(function(){m.paused=true; 
clearInterval(c); 
c=""},function(){m.paused=false; 
if(c==""&&!g.manualAdvance){c=setInterval(function(){p(d,e,g,false)},g.pauseTime)}})}d.bind("nivo:animFinished",function(){m.running=false; 
$(e).each(function(){if($(this).is("a")){$(this).css("display","none")}}); 
if($(e[m.currentSlide]).is("a")){$(e[m.currentSlide]).css("display","block")}if(c==""&&!m.paused&&!g.manualAdvance){c=setInterval(function(){p(d,e,g,false)},g.pauseTime)}g.afterChange.call(this)}); 
var p=function(r,s,u,x){var y=r.data("nivo:vars"); 
if(y&&(y.currentSlide==y.totalSlides-1)){u.lastSlide.call(this)}if((!y||y.stop)&&!x){return false}u.beforeChange.call(this); 
if(!x){r.css("background","url("+y.currentImage.attr("src")+") no-repeat")}else{if(x=="prev"){r.css("background","url("+y.currentImage.attr("src")+") no-repeat")}if(x=="next"){r.css("background","url("+y.currentImage.attr("src")+") no-repeat")}}y.currentSlide++; 
if(y.currentSlide==y.totalSlides){y.currentSlide=0; 
u.slideshowEnd.call(this)}if(y.currentSlide<0){y.currentSlide=(y.totalSlides-1)}if($(s[y.currentSlide]).is("img")){y.currentImage=$(s[y.currentSlide])}else{y.currentImage=$(s[y.currentSlide]).find("img:first")}if(u.controlNav){$(".nivo-controlNav a",r).removeClass("active"); 
$(".nivo-controlNav a:eq("+y.currentSlide+")",r).addClass("active")}if(y.currentImage.attr("title")!=""){var z=y.currentImage.attr("title"); 
if(z.substr(0,1)=="#"){z=$(z).html()}if($(".nivo-caption",r).css("display")=="block"){$(".nivo-caption p",r).fadeOut(u.animSpeed,function(){$(this).html(z); 
$(this).fadeIn(u.animSpeed)})}else{$(".nivo-caption p",r).html(z)}$(".nivo-caption",r).fadeIn(u.animSpeed)}else{$(".nivo-caption",r).fadeOut(u.animSpeed)}var w=0; 
$(".nivo-slice",r).each(function(){var i=Math.round(r.width()/u.slices); 
$(this).css({height:"0px",opacity:"0",background:"url("+y.currentImage.attr("src")+") no-repeat -"+((i+(w*i))-i)+"px 0%"}); 
w++}); 
if(u.effect=="random"){var A=new Array("sliceDownRight","sliceDownLeft","sliceUpRight","sliceUpLeft","sliceUpDown","sliceUpDownLeft","fold","fade"); 
y.randAnim=A[Math.floor(Math.random()*(A.length+1))]; 
if(y.randAnim==undefined){y.randAnim="fade"}}if(u.effect.indexOf(",")!=-1){var A=u.effect.split(","); 
y.randAnim=$.trim(A[Math.floor(Math.random()*A.length)])}y.running=true; 
if(u.effect=="sliceDown"||u.effect=="sliceDownRight"||y.randAnim=="sliceDownRight"||u.effect=="sliceDownLeft"||y.randAnim=="sliceDownLeft"){var t=0; 
var w=0; 
var C=$(".nivo-slice",r); 
if(u.effect=="sliceDownLeft"||y.randAnim=="sliceDownLeft"){C=$(".nivo-slice",r)._reverse()}C.each(function(){var i=$(this); 
i.css("top","0px"); 
if(w==u.slices-1){setTimeout(function(){i.animate({height:"100%",opacity:"1.0"},u.animSpeed,"",function(){r.trigger("nivo:animFinished")})},(100+t))}else{setTimeout(function(){i.animate({height:"100%",opacity:"1.0"},u.animSpeed)},(100+t))}t+=50; 
w++})}else{if(u.effect=="sliceUp"||u.effect=="sliceUpRight"||y.randAnim=="sliceUpRight"||u.effect=="sliceUpLeft"||y.randAnim=="sliceUpLeft"){var t=0; 
var w=0; 
var C=$(".nivo-slice",r); 
if(u.effect=="sliceUpLeft"||y.randAnim=="sliceUpLeft"){C=$(".nivo-slice",r)._reverse()}C.each(function(){var i=$(this); 
i.css("bottom","0px"); 
if(w==u.slices-1){setTimeout(function(){i.animate({height:"100%",opacity:"1.0"},u.animSpeed,"",function(){r.trigger("nivo:animFinished")})},(100+t))}else{setTimeout(function(){i.animate({height:"100%",opacity:"1.0"},u.animSpeed)},(100+t))}t+=50; 
w++})}else{if(u.effect=="sliceUpDown"||u.effect=="sliceUpDownRight"||y.randAnim=="sliceUpDown"||u.effect=="sliceUpDownLeft"||y.randAnim=="sliceUpDownLeft"){var t=0; 
var w=0; 
var B=0; 
var C=$(".nivo-slice",r); 
if(u.effect=="sliceUpDownLeft"||y.randAnim=="sliceUpDownLeft"){C=$(".nivo-slice",r)._reverse()}C.each(function(){var i=$(this); 
if(w==0){i.css("top","0px"); 
w++}else{i.css("bottom","0px"); 
w=0}if(B==u.slices-1){setTimeout(function(){i.animate({height:"100%",opacity:"1.0"},u.animSpeed,"",function(){r.trigger("nivo:animFinished")})},(100+t))}else{setTimeout(function(){i.animate({height:"100%",opacity:"1.0"},u.animSpeed)},(100+t))}t+=50; 
B++})}else{if(u.effect=="fold"||y.randAnim=="fold"){var t=0; 
var w=0; 
$(".nivo-slice",r).each(function(){var i=$(this); 
var v=i.width(); 
i.css({top:"0px",height:"100%",width:"0px"}); 
if(w==u.slices-1){setTimeout(function(){i.animate({width:v,opacity:"1.0"},u.animSpeed,"",function(){r.trigger("nivo:animFinished")})},(100+t))}else{setTimeout(function(){i.animate({width:v,opacity:"1.0"},u.animSpeed)},(100+t))}t+=50; 
w++})}else{if(u.effect=="fade"||y.randAnim=="fade"){var w=0; 
$(".nivo-slice",r).each(function(){$(this).css("height","100%"); 
if(w==u.slices-1){$(this).animate({opacity:"1.0"},(u.animSpeed*2),"",function(){r.trigger("nivo:animFinished")})}else{$(this).animate({opacity:"1.0"},(u.animSpeed*2))}w++})}}}}}}; 
var h=function(i){if(this.console&&typeof console.log!="undefined"){console.log(i)}}; 
this.stop=function(){if(!$(j).data("nivo:vars").stop){$(j).data("nivo:vars").stop=true; 
h("Stop Slider")}}; 
this.start=function(){if($(j).data("nivo:vars").stop){$(j).data("nivo:vars").stop=false; 
h("Start Slider")}}; 
g.afterLoad.call(this)};

$.fn.nivoSlider=function(c){
    return this.each(function(){
        var d=$(this);
    
        if(d.data("nivoslider")){
            return;
        }
        var e=new a(this,c);
        d.data("nivoslider",e)}
    )
};

$.fn.nivoSlider.defaults={effect:"random",slices:15,animSpeed:500,pauseTime:5000,startSlide:0,directionNav:true,directionNavHide:true,controlNav:true,controlNavThumbs:false,controlNavThumbsFromRel:false,controlNavThumbsSearch:".jpg",controlNavThumbsReplace:"_thumb.jpg",keyboardNav:true,pauseOnHover:true,manualAdvance:false,captionOpacity:0.8,beforeChange:function(){},afterChange:function(){},slideshowEnd:function(){},lastSlide:function(){},afterLoad:function(){}}; 
$.fn._reverse=[].reverse})(jQuery); 
