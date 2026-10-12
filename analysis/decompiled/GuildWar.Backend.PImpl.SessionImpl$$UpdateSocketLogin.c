// name: GuildWar.Backend.PImpl.SessionImpl$$UpdateSocketLogin
// entry: 01b31e38

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl__UpdateSocketLogin(long *param_1)

{
  long lVar1;
  int iVar2;
  long lVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  long lVar6;
  long *plVar7;
  long lVar8;
  float fVar9;
  float fVar10;
  
  if ((bRam00000000049fbe0a & 1) == 0) {
    func_0x010c65d4(0xc030);
    bRam00000000049fbe0a = 1;
  }
  fVar10 = *(float *)(param_1 + 0x14);
  fVar9 = (float)UnityEngine_Time__get_deltaTime(0,0);
  *(float *)(param_1 + 0x14) = fVar10 + fVar9;
  if ((char)param_1[2] != '\0') {
    lVar3 = (**(code **)(*param_1 + 0x1a0))(param_1,*(undefined8 *)(*param_1 + 0x1a8));
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    iVar2 = System_String__get_Length(lVar3,0);
    if ((((0 < iVar2) && (*(char *)((long)param_1 + 0x9d) != '\0')) &&
        (((*(uint *)((long)param_1 + 0xa4) & 0xff) != 0 ||
         ((*(uint *)((long)param_1 + 0xa4) & 0xff0000) == 0)))) &&
       ((*(ushort *)((long)param_1 + 0xa4) < 0x100 && (5.0 <= *(float *)(param_1 + 0x14))))) {
      lVar3 = param_1[0xf];
      plVar7 = (long *)param_1[4];
      *(undefined1 *)((long)param_1 + 0xa5) = 1;
      if (plVar7 == (long *)0x0) {
        func_0x010eec64(0);
      }
      uVar4 = (**(code **)(*plVar7 + 0x200))(plVar7,*(undefined8 *)(*plVar7 + 0x208));
      lVar8 = param_1[0xc];
      uVar5 = func_0x010fdda8(_class_<>__AnonType4<ulong,_ObscuredString>_TypeInfo);
      <>__AnonType4<ulong,_object>___ctor
                (uVar5,uVar4,lVar8,_mm_Method_<>__AnonType4<ulong,_ObscuredString>__ctor__);
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar4 = LitJson_JsonMapper__ToJson(0,uVar5,0);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = _str_login;
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,_str_login,uVar4,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar4 = System_String__Format(0,_str____0____1__,uVar5,uVar4,0);
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
      lVar8 = lVar3;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar8 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar3 + 0x48);
      if (*(long *)(lVar3 + 0x50) == 0) {
        lVar8 = func_0x010eec64(0);
      }
      uVar4 = SocketIO_Encoder__Encode(lVar8,lVar6);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar4,0);
      return;
    }
  }
  return;
}


