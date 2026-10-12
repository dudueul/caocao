// name: GuildWar.Backend.PImpl.SessionImpl$$LeaveSocketServerWorldRoom
// entry: 01b2d4dc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl__LeaveSocketServerWorldRoom
               (long param_1,int param_2,int param_3)

{
  long lVar1;
  int iVar2;
  long lVar3;
  undefined8 uVar4;
  long lVar5;
  undefined8 uVar6;
  long lVar7;
  int iVar8;
  
  if ((bRam00000000049fbe0b & 1) == 0) {
    func_0x010c65d4(0xc028);
    bRam00000000049fbe0b = 1;
  }
  if ((0 < param_2) && (0 < param_3)) {
    if ((*(int *)(param_1 + 0xac) == param_2) && (*(int *)(param_1 + 0xb0) == param_3)) {
      *(undefined8 *)(param_1 + 0xac) = 0;
    }
    else {
      iVar2 = *(int *)(param_1 + 0xcc);
      if ((iVar2 == param_2) && (*(int *)(param_1 + 0xd0) == param_3)) {
        *(int *)(param_1 + 0xb4) = param_2;
        *(int *)(param_1 + 0xb8) = param_3;
      }
      if ((*(int *)(param_1 + 0xbc) == param_2) && (*(int *)(param_1 + 0xc0) == param_3)) {
        *(int *)(param_1 + 0xb4) = param_2;
        *(int *)(param_1 + 0xb8) = param_3;
      }
      if ((0 < *(int *)(param_1 + 0xb4)) && (0 < *(int *)(param_1 + 0xb8))) {
        uVar6 = *(undefined8 *)(param_1 + 0xb4);
        *(undefined8 *)(param_1 + 0xb4) = 0;
        *(undefined8 *)(param_1 + 0xc4) = uVar6;
        if ((iVar2 == (int)uVar6) &&
           (iVar8 = (int)((ulong)uVar6 >> 0x20), *(int *)(param_1 + 0xd0) == iVar8)) {
          lVar7 = *(long *)(param_1 + 0x78);
          lVar3 = MyGuildData__get_instance(0,0);
          if (lVar3 == 0) {
            func_0x010eec64(0);
          }
          lVar3 = GuildData__get_info(lVar3,0);
          if (lVar3 == 0) {
            func_0x010eec64(0);
          }
          uVar6 = GuildInfo__get_guildId(lVar3,0);
          uVar4 = func_0x010fdda8(_class_<>__AnonType5<int,_int,_ulong>_TypeInfo);
          <>__AnonType5<int,_int,_ulong>___ctor
                    (uVar4,iVar8,iVar2,uVar6,_mm_Method_<>__AnonType5<int,_int,_ulong>__ctor__);
          if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar6 = LitJson_JsonMapper__ToJson(0,uVar4,0);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          uVar4 = _str_leaveFromWorld;
          if ((bRam0000000004a00249 & 1) == 0) {
            func_0x010c65d4(0xc5d1,_str_leaveFromWorld,uVar6,0);
            bRam0000000004a00249 = 1;
          }
          if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar6 = System_String__Format(0,_str____0____1__,uVar4,uVar6,0);
          if ((bRam0000000004a0024d & 1) == 0) {
            func_0x010c65d4(0xc5ce);
            bRam0000000004a0024d = 1;
          }
          if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar4 = LitJson_JsonMapper__ToObject(0,uVar6,0);
          lVar5 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
          uVar6 = _str__;
          System_Object___ctor(lVar5,0);
          *(undefined4 *)(lVar5 + 0x14) = 2;
          *(undefined4 *)(lVar5 + 0x18) = 0;
          *(undefined8 *)(lVar5 + 0x30) = uVar4;
          *(undefined8 *)(lVar5 + 0x20) = uVar6;
          *(undefined4 *)(lVar5 + 0x28) = 0xffffffff;
          *(undefined4 *)(lVar5 + 0x10) = 4;
          lVar3 = lVar7;
          if ((bRam0000000004a0024f & 1) == 0) {
            lVar3 = func_0x010c65d4(0xc5cf);
            bRam0000000004a0024f = 1;
          }
          lVar1 = *(long *)(lVar7 + 0x48);
          if (*(long *)(lVar7 + 0x50) == 0) {
            lVar3 = func_0x010eec64(0);
          }
          uVar6 = SocketIO_Encoder__Encode(lVar3,lVar5);
          if (lVar1 == 0) {
            func_0x010eec64(0);
          }
          WebSocketSharp_WebSocket__Send(lVar1,uVar6,0);
          return;
        }
      }
    }
  }
  return;
}


