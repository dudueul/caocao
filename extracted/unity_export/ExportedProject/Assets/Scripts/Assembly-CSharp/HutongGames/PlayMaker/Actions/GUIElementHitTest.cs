using System;
using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	[Obsolete]
	public class GUIElementHitTest : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public Camera camera;

		public FsmVector3 screenPoint;

		public FsmFloat screenX;

		public FsmFloat screenY;

		public FsmBool normalized;

		public FsmEvent hitEvent;

		public FsmBool storeResult;

		public FsmBool everyFrame;

		private GUIElement guiElement;

		private GameObject gameObjectCached;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoHitTest()
		{
		}
	}
}
