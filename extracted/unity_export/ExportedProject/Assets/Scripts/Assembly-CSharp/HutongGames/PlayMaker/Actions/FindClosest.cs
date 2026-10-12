namespace HutongGames.PlayMaker.Actions
{
	public class FindClosest : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString withTag;

		public FsmBool ignoreOwner;

		public FsmBool mustBeVisible;

		public FsmGameObject storeObject;

		public FsmFloat storeDistance;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoFindClosest()
		{
		}
	}
}
