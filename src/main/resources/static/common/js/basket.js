(function ($) {
    "use strict";
     $(document).ready(function() {
         let amount = $("#amount").val();
         let basel = $("#basel").val();

         if (basel == null || basel === "" || basel == 0) {
             basel = 2000;
         } else {
             basel = $("#basel").val();
         }

         let percent = Math.floor(amount / basel * 100);

         $("#percent").val(percent);
     });

     function setBaksetList(isSize){
         let foodlist = {}
         let nutrientList = [
            { name: '에너지', amount: 500 },
            { name: '나트륨', amount: 600 }
         ];
         sessionStorage.setItem('BasketList', JSON.stringify(nutrientList));
     }
})(jQuery);