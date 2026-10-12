// name: GuildWar.Backend.PImpl.SessionImpl$$Update
// entry: 01b2dd4c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl__Update(long param_1)

{
  long lVar1;
  long lVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  long lVar5;
  long lVar6;
  ulong uVar7;
  float fVar8;
  float fVar9;
  
  GuildWar_Backend_PImpl_SessionImpl__UpdateWebAuthKey();
  GuildWar_Backend_PImpl_SessionImpl__UpdateSocketConnect(param_1);
  GuildWar_Backend_PImpl_SessionImpl__UpdateSocketLogin(param_1);
  if ((bRam00000000049fbe0c & 1) == 0) {
    func_0x010c65d4(0xc031);
    bRam00000000049fbe0c = 1;
  }
  fVar9 = *(float *)(param_1 + 0xa8);
  fVar8 = (float)UnityEngine_Time__get_deltaTime(0,0);
  fVar9 = fVar9 + fVar8;
  *(float *)(param_1 + 0xa8) = fVar9;
  if ((((((*(char *)(param_1 + 0x10) != '\0') && (*(char *)(param_1 + 0x9d) != '\0')) &&
        (5.0 <= fVar9)) && (*(char *)(param_1 + 0xa6) != '\0')) &&
      (((*(int *)(param_1 + 0xbc) < 1 || (*(int *)(param_1 + 0xc0) < 1)) &&
       ((*(int *)(param_1 + 0xc4) < 1 || (*(int *)(param_1 + 200) < 1)))))) &&
     ((0 < *(int *)(param_1 + 0xac) && (0 < *(int *)(param_1 + 0xb0))))) {
    uVar7 = *(ulong *)(param_1 + 0xac);
    lVar6 = *(long *)(param_1 + 0x78);
    *(undefined8 *)(param_1 + 0xac) = 0;
    *(ulong *)(param_1 + 0xbc) = uVar7;
    lVar2 = MyGuildData__get_instance(0,0);
    if (lVar2 == 0) {
      func_0x010eec64(0);
    }
    lVar2 = GuildData__get_info(lVar2,0);
    if (lVar2 == 0) {
      func_0x010eec64(0);
    }
    uVar3 = GuildInfo__get_guildId(lVar2,0);
    uVar4 = func_0x010fdda8(_class_<>__AnonType5<int,_int,_ulong>_TypeInfo);
    <>__AnonType5<int,_int,_ulong>___ctor
              (uVar4,uVar7 >> 0x20,uVar7 & 0xffffffff,uVar3,
               _mm_Method_<>__AnonType5<int,_int,_ulong>__ctor__);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar3 = LitJson_JsonMapper__ToJson(0,uVar4,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = _str_enterToWorld;
    if ((bRam0000000004a00249 & 1) == 0) {
      func_0x010c65d4(0xc5d1,_str_enterToWorld,uVar3,0);
      bRam0000000004a00249 = 1;
    }
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar3 = System_String__Format(0,_str____0____1__,uVar4,uVar3,0);
    if ((bRam0000000004a0024d & 1) == 0) {
      func_0x010c65d4(0xc5ce);
      bRam0000000004a0024d = 1;
    }
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar4 = LitJson_JsonMapper__ToObject(0,uVar3,0);
    lVar5 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
    uVar3 = _str__;
    System_Object___ctor(lVar5,0);
    *(undefined4 *)(lVar5 + 0x14) = 2;
    *(undefined4 *)(lVar5 + 0x18) = 0;
    *(undefined8 *)(lVar5 + 0x30) = uVar4;
    *(undefined8 *)(lVar5 + 0x20) = uVar3;
    *(undefined4 *)(lVar5 + 0x28) = 0xffffffff;
    *(undefined4 *)(lVar5 + 0x10) = 4;
    lVar2 = lVar6;
    if ((bRam0000000004a0024f & 1) == 0) {
      lVar2 = func_0x010c65d4(0xc5cf);
      bRam0000000004a0024f = 1;
    }
    lVar1 = *(long *)(lVar6 + 0x48);
    if (*(long *)(lVar6 + 0x50) == 0) {
      lVar2 = func_0x010eec64(0);
    }
    uVar3 = SocketIO_Encoder__Encode(lVar2,lVar5);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    WebSocketSharp_WebSocket__Send(lVar1,uVar3,0);
    return;
  }
  return;
}


