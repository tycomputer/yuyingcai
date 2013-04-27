/**
 * @author Vladimir Cvetic over @ ferdinand.rs
 * @version 0.32
 * @date 22.12.2008
 * @requirements Prototype >= 1.6.0.3 && Scriptaculous >= 1.8.2
 * @url http://ferdinand.rs/javascript/
 * @notes
 * fixed several IE6+ issues.
 * added .Pause() method.
 * added .CurrentSlide() method.
 * added .SlideCount() method.
**/
if (window.Ferdinand == undefined) Ferdinand = {};
Ferdinand.Slider = Class.create({
	REQUIRED_PROTOTYPE: '1.6.0.3',
	REQUIRED_SCRIPTACULOUS: '1.8.2',
	initialize: function(element, options) {

		this.Requirements();

		element = $( element );
		this.element = element;
		
		
		this.options = options;
		this.options = Object.extend({
				effectDuration: 0.6,
				transitionDuration: 0.6,
				timeout:3500, 
				opacity:null, /*null for transparent png or value from 0 to 1 for css opacity*/
				background:'images/background.png', /*if opacity is set to null, please provide transparent background image */
				loader: 'images/ajax-loader.gif',
				next: 'images/next.png',
				previous: 'images/previous.png',
				type: 'json', /*placeholder for future releases*/
				url:"callback.json",
				titleClass: 'title',
				excerptClass: 'tekst',
				wrapbg: '#000000',
				containerClass: 'ferdinand-slider'
			}, options || {});
			
		this.PNGFix();	
			
		if (this.options.containerClass!=null && this.options.containerClass!=false && this.options.containerClass!='') {
			this.element.setAttribute('class',this.options.containerClass);	
			this.element.setAttribute('className',this.options.containerClass);	
		}
		this.CreateSubElements();
	},
	PNGFix: function () {
		var version = parseFloat(navigator.appVersion.split('MSIE')[1]);
		if ((version >= 5.5) && (version < 7) && (document.body.filters)) {
			document.getElementsByClassName('ie-fix-opacity').each(function(poElement){
				// if IE5.5+ on win32, then display PNGs with AlphaImageLoader
				var cBGImg = poElement.currentStyle.backgroundImage;
				var cImage = cBGImg.substring(cBGImg.indexOf('"') + 1, cBGImg.lastIndexOf('"'));
						
				poElement.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + cImage + "', sizingMethod='scale')";
				poElement.style.backgroundImage = "none";
			});
		}
	},
	Requirements: function ()
	{
		function convertVersionString(versionString) {
	      var v = versionString.replace(/_.*|\./g, '');
	      v = parseInt(v + '0'.times(4-v.length));
	      return versionString.indexOf('_') > -1 ? v-1 : v;
	    }		
	    if ((typeof Scriptaculous == 'undefined') ||
			(typeof Effect == 'undefined') ||
			(convertVersionString(Scriptaculous.Version) <
			convertVersionString(this.REQUIRED_SCRIPTACULOUS))) {
				throw ("nSticker requires the Scriptaculous JavaScript framework >= " +
				this.REQUIRED_SCRIPTACULOUS);
		}
		
	    if ((typeof Prototype == 'undefined') ||
			(typeof Element == 'undefined') ||
			(typeof Element.Methods == 'undefined') ||
			(convertVersionString(Prototype.Version) <
			convertVersionString(this.REQUIRED_PROTOTYPE))) {
				throw ("nSticker requires the Prototype JavaScript framework >= " +
				this.REQUIRED_PROTOTYPE);
		}	
	},
	CreateSubElements: function ()
	{
		
		this.warpcreate();
		this.imagecreate();
		this.loadercreate();
		this.navcreate();
		this.loader_active = false;
		this.GetData();
	},
	
	imagecreate: function ()
	{
		this.image = new Element('img');
		this.image.setStyle({cssFloat: 'none'});
		this.element.appendChild(this.image);
	},
	
	warpcreate: function () {
		this.warp = new Element('div');
		
		if (this.options.opacity != null) {
			this.warp.setStyle({
				background: this.options.wrapbg,
				opacity: this.options.opacity,
				position: 'absolute'
			});
		} else {
			this.warp.setStyle({
				background: "transparent url("+this.options.background+")",
				position: 'absolute'
			});			
		}
				
		this.element.appendChild(this.warp);
		this.warp.hide();
		
		if (this.options.titleClass != undefined &&
		this.options.titleClass != null) {
			this.title = new Element('a', {
				className: this.options.titleClass
			});
		}
		else 
			this.title = new Element('a');
		this.warp.appendChild(this.title);
		
		if (this.options.excerptClass != undefined &&
		this.options.excerptClass != null) {
			this.excerpt = new Element('p', {
				className: this.options.excerptClass
			});
		}
		else 
			this.excerpt = new Element('p');
		this.warp.appendChild(this.excerpt);
	},
	
	warpremove: function () {
		this.title.remove();
		this.excerpt.remove();
		this.warp.remove();
	},
	
	loadercreate: function () {
		this.loader = new Element('img');
		this.loader.hide();
		this.loader.setAttribute('src',this.options.loader);
		this.element.appendChild(this.loader);
	},
	
	navcreate: function () {
			this.nav = new Element('div');
			this.element.appendChild(this.nav);
			
			this.prevlink = new Element('a', {href: '#'}).setStyle({
				cssFloat: 'left', 
				height:'35px', 
				width:'58px', 
				display:'block', 
				background:'transparent url('+this.options.previous+') no-repeat'}).hide();
				
			this.nextlink = new Element('a', {href: '#'}).setStyle({
				cssFloat: 'right', 
				height:'35px', 
				width:'58px', 
				display:'block', 
				background:'transparent url('+this.options.next+') no-repeat'}).hide();
				
			this.nav.appendChild(this.prevlink);
			this.nav.appendChild(this.nextlink);
			
			Event.observe(this.nextlink, 'click', function(event) {
				if (this.loader_active==true) {
					return false;
				}
				if (this.timeoutInPlace)
				{
					clearTimeout(this.timeoutInPlace);
				}
				this.ShowWarp(this.current_key+1);
			}.bind(this));
			Event.observe(this.prevlink, 'click', function(event) {
				if (this.loader_active==true) {
					return false;
				}
				if (this.timeoutInPlace)
				{
					clearTimeout(this.timeoutInPlace);
				}
				this.ShowWarp(this.current_key-1);
			}.bind(this));	
	},
	Next: function () {
		this.Go(this.current_key+1);		
	},
	Prev: function () {
		this.Go(this.current_key-1);		
	},
	Go: function (num) {
		if (this.loader_active==true) {
			return false;
		}
		if (this.timeoutInPlace)
		{
			clearTimeout(this.timeoutInPlace);
		}
		this.ShowWarp(num);		
	},	
	Pause: function () {
		clearTimeout(this.timeoutInPlace);
	},
	SlideCount: function () {
		return this.slide_count;
	},
	CurrentSlide: function () {
		return this.current_key;
	},
	navhide: function () {
		this.prevlink.hide();
		this.nextlink.hide();
	},
	navsetevents: function () {

		var containerLeft = Position.page(this.image)[0];
		var containerTop = Position.page(this.image)[1];
		

		var containerDimensions = this.image.getDimensions();
		var height = containerDimensions.height;
		var width = containerDimensions.width;
		
		Event.observe(document.body, 'mousemove', function(event) {
			
			mouseX = Event.pointerX(event);
			mouseY = Event.pointerY(event);	
			horizontalPosition = mouseX - containerLeft;
			verticalPosition = mouseY - containerTop;
			if(horizontalPosition <0 || verticalPosition <0 || mouseX> (width + containerLeft) || mouseY> (height + containerTop) ){
				this.prevlink.hide();
				this.nextlink.hide();
				
			}else{
				val = this.image.getWidth()/2;
				offset = this.image.viewportOffset();
				val = offset.left + val;			
				if (mouseX>val) {
					if (!this.no_next) {
						this.nextlink.show();
					}
					this.prevlink.hide();
				}
				if (mouseX<val) {
					if (!this.no_prev) {
						this.prevlink.show();
					}
					this.nextlink.hide();
				}		
			}
				
		}.bind(this));
	},
	navposition: function () {
		img = {};
		img.w = this.image.getWidth();
		img.h = this.image.getHeight();
		this.nav.setStyle({position:'absolute', width:img.w});
		pos = this.image.positionedOffset();
		this.nav.setStyle({
			zIndex: 2, 
			position:'absolute',
			width:img.w+'px',
			left:pos.left+'px',
			top:pos.top+(img.h/2-30)+'px'});
			
		this.navsetevents();
	},
	
	setLoader: function (status) {
				
		if (status=='on') this.loader_active = true;
		else this.loader_active = false;
						
		if (!this.loader) return false;
		
		if (!this.image.src || this.image.src=='undefined') {
			return false;
		}
		pos = this.image.positionedOffset();

		this.loader.setStyle({
			zIndex: 2, 
			position:'absolute',
			left:pos.left + this.image.getWidth() -  this.loader.getWidth() - 4+'px',
			top:pos.top + this.image.getHeight() -  this.loader.getHeight() - 4+'px'});
		
		if (status=='on') this.loader.show();
		else  this.loader.hide();
	},
	GetData: function () {
		this.setLoader('on');
		new Ajax.Request(this.options.url, {
			method:'get',
			requestHeaders: {Accept: 'application/json'},
			onSuccess: function(t){
				this.setLoader('off');
				this.items = t.responseText.evalJSON(true);
				this.slide_count = this.items.length;
				this.ShowWarp(0);
			}.bind(this)
		});
	},
	ShowWarp: function (key) {
		this.setLoader('on');
		var itemkey = key+1;
		this.current_key = key;
		var item = this.items[key];
		this.no_next = false;
		this.no_prev = false;
		
		if (key==this.items.length) {
			this.no_next = true;
			this.GetData();
			return;
		}
		if (key==0) {
			this.no_prev = true;
		}
		
		this.tempimage = new Element('img');
		this.tempimage.setAttribute('src', item.image);

		if (this.tempimage.complete) {
			this.StartTransition(item, itemkey);
		} else {
			this.tempimage.observe('load', function(){
				 this.StartTransition(item, itemkey);
			}.bind(this));				
		}
	},
	StartTransition: function (item, itemkey) {
				new Effect.BlindUp(this.warp, {
					duration: this.options.effectDuration,
					afterFinish: function(){
							this.warpremove();
							this.warpcreate();
							this.warp.style.width = this.tempimage.width+'px';
							this.element.style.height = this.tempimage.height+'px';
							this.tempimage.hide();
							this.navhide();
							new Effect.Fade(this.image, {
								from: 1.0, 
								to: 0.0, 
								duration: 
								this.options.effectDuration,
								afterFinish: function(){
									this.image.remove();
									this.image = this.tempimage;
									this.element.appendChild(this.image);									
									this.BeginSlide(item, itemkey);
								}.bind(this)});
						
					}.bind(this)});	
	},
	BeginSlide: function (item, itemkey)
	{
		if (item.url!=null) this.title.setAttribute('href',item.url);
		if (item.title!=null) this.title.update(item.title)
		if (item.body!=null) this.excerpt.update(item.body);	
		this.setLoader('off');						
		new Effect.Appear(this.image,{ 
						from: 0.0, to: 1.0, 
						duration: this.options.effectDuration,
						afterFinish: function(){	
							this.navposition();
							new Effect.BlindDown(this.warp,{
								duration: this.options.effectDuration, 
								afterFinish: function(){
									if (this.timeoutInPlace)
									{
										clearTimeout(this.timeoutInPlace);
									}
									this.timeoutInPlace = setTimeout(function () { 
												this.ShowWarp(itemkey); 
											}.bind(this), this.options.timeout);
									}.bind(this)
							});								
						}.bind(this)
					});
	}
});