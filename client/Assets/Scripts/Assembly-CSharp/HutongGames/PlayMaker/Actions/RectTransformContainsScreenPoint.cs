using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformContainsScreenPoint : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector2 screenPointVector2;

		public FsmVector3 orScreenPointVector3;

		public bool normalizedScreenPoint;

		public FsmGameObject camera;

		public bool everyFrame;

		public FsmBool isContained;

		public FsmEvent isContainedEvent;

		public FsmEvent isNotContainedEvent;

		private RectTransform _rt;

		private Camera _camera;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoCheck()
		{
		}
	}
}
