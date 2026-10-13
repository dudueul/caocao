// name: Multiplay.MultiplayManager$$EmitSticker
// entry: 013a475c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__EmitSticker(long param_1,long *param_2)

{
  long lVar1;
  undefined4 uVar2;
  ulong uVar3;
  long *plVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  long lVar8;
  long lVar9;
  code *pcVar10;
  long lVar11;
  
  if ((bRam00000000049f7aed & 1) == 0) {
    func_0x010c65d4(0x8a2b);
    bRam00000000049f7aed = 1;
  }
  if (((*(char *)(param_1 + 0x141) == '\0') || (*(long *)(param_1 + 0x40) == 0)) ||
     (*(long *)(param_1 + 0x48) == 0)) {
    return;
  }
  plVar4 = (long *)func_0x010fdda8(_class_LitJson_JsonWriter_TypeInfo);
  LitJson_JsonWriter___ctor(plVar4,0);
  if (param_2 == (long *)0x0) {
    func_0x010eec64(0);
  }
  (**(code **)(*param_2 + 0x170))(param_2,plVar4,*(undefined8 *)(*param_2 + 0x178));
  lVar11 = *(long *)(param_1 + 0x48);
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = BattlePlay_BattlePlayData__get_battleKey(lVar11,0);
  if (plVar4 == (long *)0x0) {
    func_0x010eec64(0);
  }
  uVar6 = (**(code **)(*plVar4 + 0x160))(plVar4,*(undefined8 *)(*plVar4 + 0x168));
  plVar4 = *(long **)(param_1 + 0x40);
  if (plVar4 == (long *)0x0) {
    uVar2 = 8;
  }
  else {
    uVar2 = (**(code **)(*plVar4 + 0x170))(plVar4,*(undefined8 *)(*plVar4 + 0x178));
  }
  uVar7 = func_0x010fdda8(_class_<>__AnonType37<string,_string,_Step>_TypeInfo);
  <>__AnonType37<string,_string,_Step>___ctor
            (uVar7,uVar5,uVar6,uVar2,_mm_Method_<>__AnonType37<string,_string,_Step>__ctor__);
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = LitJson_JsonMapper__ToJson(0,uVar7,0);
  uVar5 = _str_sticker;
  uVar3 = MyUserData__get_hasInstance(0,0);
  if ((uVar3 & 1) != 0) {
    lVar11 = MyUserData__get_instance(0,0);
    if (lVar11 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar11 + 0x268) != '\0') {
      lVar11 = *(long *)(param_1 + 0x30);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar5,uVar6,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar5 = System_String__Format(0,_str____0____1__,uVar5,uVar6,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar6 = LitJson_JsonMapper__ToObject(0,uVar5,0);
      lVar8 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar5 = _str__;
      System_Object___ctor(lVar8,0);
      *(undefined4 *)(lVar8 + 0x14) = 2;
      *(undefined4 *)(lVar8 + 0x18) = 0;
      *(undefined8 *)(lVar8 + 0x30) = uVar6;
      *(undefined8 *)(lVar8 + 0x20) = uVar5;
      *(undefined4 *)(lVar8 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar8 + 0x10) = 4;
      lVar9 = lVar11;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar9 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar11 + 0x48);
      if (*(long *)(lVar11 + 0x50) == 0) {
        lVar9 = func_0x010eec64(0);
      }
      uVar5 = SocketIO_Encoder__Encode(lVar9,lVar8);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar5,0);
      return;
    }
  }
  uVar3 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar3 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined1 *)(param_1 + 0x141) = 0;
  *(undefined1 *)(param_1 + 0x142) = 0;
  uVar5 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = UnityEngine_Object__op_Inequality(0,uVar5,0,0);
  if ((uVar3 & 1) == 0) {
    return;
  }
  lVar11 = *(long *)(param_1 + 0x30);
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__Close(lVar11,0);
  uVar5 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam0000000004a04120 & 1) == 0) {
    func_0x010c65d4(0x906e,uVar5,0);
    bRam0000000004a04120 = 1;
  }
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  pcVar10 = pcRam0000000004a04118;
  if ((pcRam0000000004a04118 == (code *)0x0) &&
     (pcVar10 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar10 == (code *)0x0)) {
    uVar6 = func_0x010f000c(&UNK_039a20c7);
    func_0x010eebec(uVar6,0,0);
  }
  pcRam0000000004a04118 = pcVar10;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*pcRam0000000004a04118)(uVar5,0);
  return;
}


