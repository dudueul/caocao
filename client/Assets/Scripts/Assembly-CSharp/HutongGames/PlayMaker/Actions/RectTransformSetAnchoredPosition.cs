using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformSetAnchoredPosition : BaseUpdateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector2 position;

		public FsmFloat x;

		public FsmFloat y;

		private RectTransform _rt;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void DoSetAnchoredPosition()
		{
		}
	}
}
