// name: Multiplay.MultiplayManager$$OnConnected
// entry: 013a6e18

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnConnected(long param_1)

{
  long lVar1;
  undefined4 uVar2;
  undefined4 uVar3;
  undefined4 uVar4;
  undefined4 uVar5;
  ulong uVar6;
  long lVar7;
  long *plVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  long lVar12;
  long lVar13;
  code *pcVar14;
  undefined8 uVar15;
  
  if (*(long *)(param_1 + 0x68) != 0) {
    System_Action__Invoke(*(long *)(param_1 + 0x68),0);
  }
  if ((bRam00000000049f7ace & 1) == 0) {
    func_0x010c65d4(0x8a7a);
    bRam00000000049f7ace = 1;
  }
  lVar7 = MyUserData__get_instance(0,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  plVar8 = (long *)UserData__get_info(lVar7,0);
  if (plVar8 == (long *)0x0) {
    func_0x010eec64(0);
  }
  uVar9 = (**(code **)(*plVar8 + 0x1a0))(plVar8,*(undefined8 *)(*plVar8 + 0x1a8));
  lVar7 = MyUserData__get_instance(0,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  lVar7 = UserData__get_info(lVar7,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar10 = UserInfo__get_nationName(lVar7,0);
  lVar7 = MyUserData__get_instance(0,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  lVar7 = UserData__get_info(lVar7,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar2 = UserInfo__get_VisibleFaceUnitID(lVar7,0);
  lVar7 = MyUserData__get_instance(0,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  plVar8 = (long *)UserData__get_info(lVar7,0);
  if (plVar8 == (long *)0x0) {
    func_0x010eec64(0);
  }
  uVar3 = (**(code **)(*plVar8 + 0x1e0))(plVar8,*(undefined8 *)(*plVar8 + 0x1e8));
  lVar7 = MyUserData__get_instance(0,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  lVar7 = UserData__get_info(lVar7,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = UserInfo__get_level(lVar7,0);
  uVar15 = *(undefined8 *)(param_1 + 0x38);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Util_TypeInfo);
  }
  uVar5 = Util__GetRevisionNumber(0,0);
  uVar11 = func_0x010fdda8(
                          _class_<>__AnonType13<ulong,_string,_int,_int,_int,_ObscuredString,_int>_TypeInfo
                          );
  <>__AnonType13<ulong,_string,_int,_int,_int,_ObscuredString,_int>___ctor
            (uVar11,uVar9,uVar10,uVar2,uVar3,uVar4,uVar15,uVar5,
             _mm_Method_<>__AnonType13<ulong,_string,_int,_int,_int,_ObscuredString,_int>__ctor__);
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar10 = LitJson_JsonMapper__ToJson(0,uVar11,0);
  uVar9 = _str_register;
  uVar6 = MyUserData__get_hasInstance(0,0);
  if ((uVar6 & 1) != 0) {
    lVar7 = MyUserData__get_instance(0,0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar7 + 0x268) != '\0') {
      lVar7 = *(long *)(param_1 + 0x30);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar9,uVar10,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar9 = System_String__Format(0,_str____0____1__,uVar9,uVar10,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = LitJson_JsonMapper__ToObject(0,uVar9,0);
      lVar12 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar9 = _str__;
      System_Object___ctor(lVar12,0);
      *(undefined4 *)(lVar12 + 0x14) = 2;
      *(undefined4 *)(lVar12 + 0x18) = 0;
      *(undefined8 *)(lVar12 + 0x30) = uVar10;
      *(undefined8 *)(lVar12 + 0x20) = uVar9;
      *(undefined4 *)(lVar12 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar12 + 0x10) = 4;
      lVar13 = lVar7;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar13 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar7 + 0x48);
      if (*(long *)(lVar7 + 0x50) == 0) {
        lVar13 = func_0x010eec64(0);
      }
      uVar9 = SocketIO_Encoder__Encode(lVar13,lVar12);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar9,0);
      return;
    }
  }
  uVar6 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar6 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined1 *)(param_1 + 0x141) = 0;
  *(undefined1 *)(param_1 + 0x142) = 0;
  uVar9 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = UnityEngine_Object__op_Inequality(0,uVar9,0,0);
  if ((uVar6 & 1) == 0) {
    return;
  }
  lVar7 = *(long *)(param_1 + 0x30);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__Close(lVar7,0);
  uVar9 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam0000000004a04120 & 1) == 0) {
    func_0x010c65d4(0x906e,uVar9,0);
    bRam0000000004a04120 = 1;
  }
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  pcVar14 = pcRam0000000004a04118;
  if ((pcRam0000000004a04118 == (code *)0x0) &&
     (pcVar14 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar14 == (code *)0x0)) {
    uVar10 = func_0x010f000c(&UNK_039a20c7);
    func_0x010eebec(uVar10,0,0);
  }
  pcRam0000000004a04118 = pcVar14;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*pcRam0000000004a04118)(uVar9,0);
  return;
}


