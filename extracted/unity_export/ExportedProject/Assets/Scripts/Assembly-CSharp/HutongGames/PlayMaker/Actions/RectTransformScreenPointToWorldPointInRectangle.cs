using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformScreenPointToWorldPointInRectangle : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector2 screenPointVector2;

		public FsmVector3 orScreenPointVector3;

		public bool normalizedScreenPoint;

		public FsmGameObject camera;

		public bool everyFrame;

		public FsmVector3 worldPosition;

		public FsmBool isHit;

		public FsmEvent hitEvent;

		public FsmEvent noHitEvent;

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
