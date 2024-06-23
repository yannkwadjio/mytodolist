let sMenuList=document.querySelector('.menuListe');
let sLibAccueil=document.querySelector('.libAccueil');
sMenuList.addEventListener('mouseover',()=>{
    sLibAccueil.style.display='inline'
    
});

sMenuList.addEventListener('mouseout',()=>{
    sLibAccueil.style.display='none'
    
});