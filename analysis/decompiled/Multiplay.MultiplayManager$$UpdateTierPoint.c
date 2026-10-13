// name: Multiplay.MultiplayManager$$UpdateTierPoint
// entry: 013a17c4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__UpdateTierPoint(long param_1,undefined4 param_2,undefined4 param_3)

{
  long lVar1;
  ulong uVar2;
  long lVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  long lVar6;
  long lVar7;
  code *pcVar8;
  
  if ((bRam00000000049f7ad0 & 1) == 0) {
    func_0x010c65d4(0x8aae);
    bRam00000000049f7ad0 = 1;
  }
  if (*(char *)(param_1 + 0x141) == '\0') {
    return;
  }
  uVar4 = func_0x010fdda8(_class_<>__AnonType14<int,_int>_TypeInfo);
  <>__AnonType14<int,_int>___ctor(uVar4,param_2,param_3,_mm_Method_<>__AnonType14<int,_int>__ctor__)
  ;
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = LitJson_JsonMapper__ToJson(0,uVar4,0);
  uVar4 = _str_updateTierPoint;
  uVar2 = MyUserData__get_hasInstance(0,0);
  if ((uVar2 & 1) != 0) {
    lVar3 = MyUserData__get_instance(0,0);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar3 + 0x268) != '\0') {
      lVar3 = *(long *)(param_1 + 0x30);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar4,uVar5,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar4 = System_String__Format(0,_str____0____1__,uVar4,uVar5,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar5 = LitJson_JsonMapper__ToObject(0,uVar4,0);
      lVar6 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar4 = _str__;
      System_Object___ctor(lVar6,0);
      *(undefined4 *)(lVar6 + 0x14) = 2;
      *(undefined4 *)(lVar6 + 0x18) = 0;
      *(undefined8 *)(lVar6 + 0x30) = uVar5;
      *(undefined8 *)(lVar6 + 0x20) = uVar4;
      *(undefined4 *)(lVar6 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar6 + 0x10) = 4;
      lVar7 = lVar3;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar7 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar3 + 0x48);
      if (*(long *)(lVar3 + 0x50) == 0) {
        lVar7 = func_0x010eec64(0);
      }
      uVar4 = SocketIO_Encoder__Encode(lVar7,lVar6);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar4,0);
      return;
    }
  }
  uVar2 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar2 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined1 *)(param_1 + 0x141) = 0;
  *(undefined1 *)(param_1 + 0x142) = 0;
  uVar4 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = UnityEngine_Object__op_Inequality(0,uVar4,0,0);
  if ((uVar2 & 1) == 0) {
    return;
  }
  lVar3 = *(long *)(param_1 + 0x30);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__Close(lVar3,0);
  uVar4 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam0000000004a04120 & 1) == 0) {
    func_0x010c65d4(0x906e,uVar4,0);
    bRam0000000004a04120 = 1;
  }
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  pcVar8 = pcRam0000000004a04118;
  if ((pcRam0000000004a04118 == (code *)0x0) &&
     (pcVar8 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar8 == (code *)0x0)) {
    uVar5 = func_0x010f000c(&UNK_039a20c7);
    func_0x010eebec(uVar5,0,0);
  }
  pcRam0000000004a04118 = pcVar8;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*pcRam0000000004a04118)(uVar4,0);
  return;
}


