// name: GuildWar.Backend.PImpl.SessionImpl$$CreateSocket
// entry: 01b323f4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl__CreateSocket(long param_1)

{
  int iVar1;
  uint uVar2;
  ulong uVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  long lVar7;
  long lVar8;
  long lVar9;
  
  if ((bRam00000000049fbe08 & 1) == 0) {
    func_0x010c65d4(0xc026);
    bRam00000000049fbe08 = 1;
  }
  uVar6 = *(undefined8 *)(param_1 + 0x78);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = UnityEngine_Object__op_Inequality(0,uVar6,0,0);
  if ((uVar3 & 1) != 0) {
    lVar7 = *(long *)(param_1 + 0x78);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar7,0);
    uVar6 = *(undefined8 *)(param_1 + 0x78);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    UnityEngine_Object__DestroyImmediate(0,uVar6,0);
  }
  uVar6 = *(undefined8 *)(param_1 + 0x18);
  if (((*(byte *)(_class_NGUITools_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_NGUITools_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar7 = NGUITools__AddMissingComponent<VipLevelUpEffect>
                    (0,uVar6,_mm_Method_NGUITools_AddMissingComponent<SocketIOComponent>__);
  *(long *)(param_1 + 0x78) = lVar7;
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__set_reconnectDelay(lVar7,5,0);
  lVar7 = *(long *)(param_1 + 0x78);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  *(undefined1 *)(lVar7 + 0x20) = 0;
  uVar6 = _mm_Method_GuildWar_Backend_PImpl_SessionImpl_OnSocketConnect__;
  lVar7 = *(long *)(param_1 + 0x78);
  uVar4 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar4,param_1,uVar6,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__On(lVar7,_str_connect,uVar4,0);
  uVar6 = _mm_Method_GuildWar_Backend_PImpl_SessionImpl_OnSocketDisconnect__;
  lVar7 = *(long *)(param_1 + 0x78);
  uVar4 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar4,param_1,uVar6,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__On(lVar7,_str_disconnect,uVar4,0);
  uVar6 = _mm_Method_GuildWar_Backend_PImpl_SessionImpl_OnSocketLogined__;
  lVar7 = *(long *)(param_1 + 0x78);
  uVar4 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar4,param_1,uVar6,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__On(lVar7,_str_logined,uVar4,0);
  uVar6 = _mm_Method_GuildWar_Backend_PImpl_SessionImpl_OnSocketError__;
  lVar7 = *(long *)(param_1 + 0x78);
  uVar4 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar4,param_1,uVar6,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__On(lVar7,_str_error,uVar4,0);
  uVar6 = _mm_Method_GuildWar_Backend_PImpl_SessionImpl_OnSocketEnterToWorld__;
  lVar7 = *(long *)(param_1 + 0x78);
  uVar4 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar4,param_1,uVar6,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__On(lVar7,_str_enterToWorld,uVar4,0);
  uVar6 = _mm_Method_GuildWar_Backend_PImpl_SessionImpl_OnSocketUpdateHistory__;
  lVar7 = *(long *)(param_1 + 0x78);
  uVar4 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar4,param_1,uVar6,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar6 = _str_updateHistory;
  if ((bRam0000000004a00244 & 1) == 0) {
    func_0x010c65d4(0xc5de,_str_updateHistory,uVar4,0);
    bRam0000000004a00244 = 1;
  }
  lVar8 = *(long *)(lVar7 + 0x68);
  if (lVar8 == 0) {
    func_0x010eec64(0);
  }
  uVar2 = System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__ContainsKey
                    (lVar8,uVar6,
                     _mm_Method_System_Collections_Generic_Dictionary<string,_List<Action<SocketIOEvent>>>_ContainsKey__
                    );
  if ((uVar2 & 1) == 0) {
    lVar8 = *(long *)(lVar7 + 0x68);
    uVar5 = func_0x010fdda8(_class_System_Collections_Generic_List<Action<SocketIOEvent>>_TypeInfo);
    System_Collections_Generic_List<tk2dUILayoutItem>___ctor
              (uVar5,_mm_Method_System_Collections_Generic_List<Action<SocketIOEvent>>__ctor__);
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__set_Item
              (lVar8,uVar6,uVar5,
               _mm_Method_System_Collections_Generic_Dictionary<string,_List<Action<SocketIOEvent>>>_set_Item__
              );
  }
  lVar7 = *(long *)(lVar7 + 0x68);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  lVar7 = System_Collections_Generic_Dictionary<object,_object>__get_Item
                    (lVar7,uVar6,
                     _mm_Method_System_Collections_Generic_Dictionary<string,_List<Action<SocketIOEvent>>>_get_Item__
                    );
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  lVar8 = _mm_Method_System_Collections_Generic_List<Action<SocketIOEvent>>_Add__;
  iVar1 = *(int *)(lVar7 + 0x18);
  lVar9 = *(long *)(lVar7 + 0x10);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  if (iVar1 == *(int *)(lVar9 + 0x18)) {
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar8 + 0x18) + 0xc0) + 0x88))(lVar7,1);
  }
  uVar2 = *(uint *)(lVar7 + 0x18);
  lVar8 = *(long *)(lVar7 + 0x10);
  *(uint *)(lVar7 + 0x18) = uVar2 + 1;
  if (lVar8 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar8 + 0x18) <= uVar2) {
    uVar6 = func_0x010ef850();
    func_0x010eebec(uVar6,0,0);
  }
  *(undefined8 *)(lVar8 + (long)(int)uVar2 * 8 + 0x20) = uVar4;
  *(int *)(lVar7 + 0x1c) = *(int *)(lVar7 + 0x1c) + 1;
  return;
}


