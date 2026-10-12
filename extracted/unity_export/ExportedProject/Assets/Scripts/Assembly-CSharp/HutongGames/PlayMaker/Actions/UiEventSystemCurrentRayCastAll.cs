using System.Collections.Generic;
using UnityEngine.EventSystems;

namespace HutongGames.PlayMaker.Actions
{
	public class UiEventSystemCurrentRayCastAll : FsmStateAction
	{
		[RequiredField]
		public FsmVector3 screenPosition;

		public FsmVector2 orScreenPosition2d;

		public FsmArray gameObjectList;

		public FsmInt hitCount;

		public bool everyFrame;

		private PointerEventData pointer;

		private List<RaycastResult> raycastResults;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void ExecuteRayCastAll()
		{
		}
	}
}
