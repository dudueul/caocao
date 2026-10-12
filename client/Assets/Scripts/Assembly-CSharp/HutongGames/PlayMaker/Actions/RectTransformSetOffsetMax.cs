using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformSetOffsetMax : BaseUpdateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector2 offsetMax;

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

		private void DoSetOffsetMax()
		{
		}
	}
}
