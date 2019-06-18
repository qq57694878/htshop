// pages/main/index.js
var QR =
    require( "../../qrcode/qrcode.js" );
var barcode =
    require( "../../qrcode/barcode.js" );


var module;
var st;
var page ;
Page( {
    data: {
        barcode: '',
    },


    onLoad: function ( options )
    {
        page = this;
      module = new Module(options.barCode);
        module.loadCardCode()


    },
    onReady: function ()
    {


    },
    onShow: function ()
    {
        module.loadCardCode()
        // 页面显示
    },
    onHide: function ()
    {
        // 页面隐藏
    },

    onUnload: function ()
    {
        // 页面关闭

    },

    render: function ( card )
    {
        var w = 330
        var h = 100
        barcode.createBarcode( 'barcodeCanvas', card, w, h );
        var w2 = 250
        QR.qrApi.draw( card, "qrcodeCanvas", w2, w2 );
        var code = barcode.formatBarCode( card, " " )

        this.setData( {
            barcode: code
        } )

        clearTimeout( st );
        st = setTimeout( function ()
            {
                module.loadCardCode()
                clearTimeout( st );
            },
            10 * 1000
            // 4000
        );
    },


    refresh: function ( e )
    {

        module.loadCardCode()
    }


} )




class Module {

    
    constructor( barCode)
    {
      this.barCode = barCode
    }

    loadCardCode()
    {

      page.render(this.barCode);

    }
}