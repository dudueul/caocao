// name: Multiplay.MultiplayManager$$Connect
// entry: 0139fd30

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__Connect(long param_1)

{
  long lVar1;
  undefined4 uVar2;
  undefined8 uVar3;
  long *plVar4;
  undefined8 uVar5;
  long lVar6;
  long *plVar7;
  undefined8 uVar8;
  undefined8 uStack_48;
  
  if ((bRam00000000049f7aca & 1) == 0) {
    func_0x010c65d4(0x8a21);
    bRam00000000049f7aca = 1;
  }
  Multiplay_MultiplayManager__Disconnect(param_1);
  uVar5 = _mm_Method_Multiplay_MultiplayManager_ConnectToMultiplayServer__;
  uVar8 = *(undefined8 *)(param_1 + 0x70);
  uVar3 = func_0x010fdda8(_class_System_Action_TypeInfo);
  System_Action___ctor(uVar3,param_1,uVar5,0);
  plVar4 = (long *)System_Delegate__Combine(0,uVar8,uVar3,0);
  plVar7 = (long *)0x0;
  if (plVar4 != (long *)0x0) {
    plVar7 = plVar4;
    if (*plVar4 != _class_System_Action_TypeInfo) {
      func_0x0111ef28(&uStack_48,*(undefined8 *)(*plVar4 + 0x40));
      uVar5 = func_0x010ef81c(uStack_48);
      func_0x010eebec(uVar5,0,0);
      func_0x036457e0(&uStack_48);
      plVar7 = (long *)0x0;
    }
  }
  *(long **)(param_1 + 0x70) = plVar7;
  lVar6 = UnityEngine_Component__get_gameObject(param_1,0);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  lVar6 = UnityEngine_GameObject__AddComponent<object>
                    (lVar6,_mm_Method_UnityEngine_GameObject_AddComponent<SocketIOComponent>__);
  *(long *)(param_1 + 0x30) = lVar6;
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__set_reconnectDelay(lVar6,3,0);
  lVar6 = *(long *)(param_1 + 0x30);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  *(undefined4 *)(lVar6 + 0x2c) = 5;
  lVar6 = *(long *)(param_1 + 0x30);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar6 + 0x30) = 0x4014000000000000;
  lVar6 = *(long *)(param_1 + 0x30);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  *(undefined1 *)(lVar6 + 0x20) = 0;
  Multiplay_MultiplayManager__RegisterCallback(param_1);
  lVar6 = *(long *)(param_1 + 0x28);
  lVar1 = *(long *)(param_1 + 0x30);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = Multiplay_MultiplayManager_ServerInfo__get_Uri(lVar6,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__set_URL(lVar1,uVar5,0);
  lVar6 = *(long *)(param_1 + 0x30);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__Connect(lVar6,0);
  uVar2 = UnityEngine_Application__get_internetReachability(0,0);
  *(undefined4 *)(param_1 + 0x1c) = uVar2;
  uVar2 = UnityEngine_Time__get_realtimeSinceStartup(0,0);
  *(undefined4 *)(param_1 + 0x20) = uVar2;
  return;
}


