// name: Multiplay.MultiplayManager.<CancelMatchmaking>c__AnonStorey4$$<>m__0
// entry: 01f5ef34

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager_<CancelMatchmaking>c__AnonStorey4__<>m__0(long param_1)

{
  undefined8 uVar1;
  long lVar2;
  ulong uVar3;
  undefined8 uVar4;
  undefined8 *puVar5;
  long lVar6;
  
  if ((bRam00000000049fe338 & 1) == 0) {
    func_0x010c65d4(0xdafa);
    bRam00000000049fe338 = 1;
  }
  lVar6 = *(long *)(param_1 + 0x10);
  uVar1 = ___L(0,_str_나가기,0);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  PanelPopup2Buttons__set_title(lVar6,uVar1,0);
  lVar6 = *(long *)(param_1 + 0x10);
  lVar2 = *(long *)(param_1 + 0x18);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  lVar2 = Multiplay_MultiplayManager__get_battlePlayData(lVar2,0);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = BattlePlay_BattlePlayData__get_friendly(lVar2,0);
  if ((uVar3 & 1) == 0) {
    puVar5 = (undefined8 *)&DAT_049e6740;
  }
  else {
    puVar5 = (undefined8 *)&str_현재_화면에서_나가시겠습니까_;
  }
  uVar1 = ___L(0,*puVar5,0);
  uVar4 = func_0x01101ad4(_class_object___TypeInfo,0);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  PanelPopup2Buttons__SetLabel2(lVar6,uVar1,uVar4,0);
  lVar6 = *(long *)(param_1 + 0x10);
  uVar1 = ___L(0,_str_나가기,0);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  if ((bRam00000000049ff0a9 & 1) == 0) {
    func_0x010c65d4(0x9798,uVar1,0);
    bRam00000000049ff0a9 = 1;
  }
  lVar2 = PanelPopup2Buttons__get__1btn(lVar6);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  UnityEngine_GameObject__SetActive(lVar2,0,0);
  lVar2 = PanelPopup2Buttons__get__2btn(lVar6);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  UnityEngine_GameObject__SetActive(lVar2,1,0);
  lVar2 = PanelPopup2Buttons__get__2btnReverse(lVar6);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  UnityEngine_GameObject__SetActive(lVar2,0,0);
  lVar2 = PanelPopup2Buttons__get_btnYes(lVar6);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  UnityEngine_GameObject__SetActive(lVar2,1,0);
  lVar2 = PanelPopup2Buttons__get_btnPurchase(lVar6);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  UnityEngine_GameObject__SetActive(lVar2,0,0);
  lVar2 = PanelPopup2Buttons__get_btnPurchase2(lVar6);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  UnityEngine_GameObject__SetActive(lVar2,0,0);
  lVar2 = PanelPopup2Buttons__get_btnLackGoodsPurchase(lVar6);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  UnityEngine_GameObject__SetActive(lVar2,0,0);
  lVar2 = PanelPopup2Buttons__get_btnLackGoodsCancel(lVar6);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  UnityEngine_GameObject__SetActive(lVar2,0,0);
  lVar2 = PanelPopup2Buttons__get_btnCancel(lVar6);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  UnityEngine_GameObject__SetActive(lVar2,1,0);
  lVar2 = PanelPopup2Buttons__get_btnConfirmOneBtn(lVar6);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  UnityEngine_GameObject__SetActive(lVar2,0,0);
  lVar2 = PanelPopup2Buttons__get_btnTicket(lVar6);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  UnityEngine_GameObject__SetActive(lVar2,0,0);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = System_String__op_Inequality(0,uVar1,**(undefined8 **)(_class_string_TypeInfo + 0xb8),0);
  if ((uVar3 & 1) != 0) {
    lVar6 = PanelPopup2Buttons__get_btnYes(lVar6);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = UnityEngine_GameObject__get_transform(lVar6,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = UnityEngine_Transform__Find(lVar6,_str_Label,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = UnityEngine_Component__get_gameObject(lVar6,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = UnityEngine_GameObject__GetComponent<object>
                      (lVar6,_mm_Method_UnityEngine_GameObject_GetComponent<UILabelLocalized>__);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    if ((bRam00000000049ff6b6 & 1) == 0) {
      func_0x010c65d4(0xe6fe,uVar1,0);
      bRam00000000049ff6b6 = 1;
    }
    lVar2 = *(long *)(lVar6 + 0x278);
    *(undefined1 *)(lVar6 + 0x29a) = 0;
    if (lVar2 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_List<tk2dUICamera>__Clear
              (lVar2,_mm_Method_System_Collections_Generic_List<UILabelLocalized_AppendCtx>_Clear__)
    ;
    *(undefined1 *)(lVar6 + 0x288) = 1;
    *(undefined8 *)(lVar6 + 0x268) = uVar1;
    *(undefined8 *)(lVar6 + 0x270) = 0;
    *(undefined1 *)(lVar6 + 0x299) = 0;
    return;
  }
  return;
}


