namespace HutongGames.PlayMaker.Actions
{
	public class ArrayForEach : RunFSMAction
	{
		[RequiredField]
		public FsmArray array;

		[HideTypeFilter]
		public FsmVar storeItem;

		public FsmTemplateControl fsmTemplateControl;

		public FsmEvent finishEvent;

		private int currentIndex;

		public override void Reset()
		{
		}

		public override void Awake()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		public override void OnFixedUpdate()
		{
		}

		public override void OnLateUpdate()
		{
		}

		private void StartNextFsm()
		{
		}

		private void StartFsm()
		{
		}

		private void DoStartFsm()
		{
		}

		protected override void CheckIfFinished()
		{
		}
	}
}
