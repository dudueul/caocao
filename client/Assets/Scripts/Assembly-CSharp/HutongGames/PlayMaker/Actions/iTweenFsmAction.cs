namespace HutongGames.PlayMaker.Actions
{
	public abstract class iTweenFsmAction : FsmStateAction, ITweenCallback
	{
		public enum AxisRestriction
		{
			none = 0,
			x = 1,
			y = 2,
			z = 3,
			xy = 4,
			xz = 5,
			yz = 6
		}

		public FsmEvent startEvent;

		public FsmEvent finishEvent;

		public FsmBool realTime;

		public FsmBool stopOnExit;

		public FsmBool loopDontFinish;

		internal iTweenFSMEvents itweenEvents;

		protected string itweenType;

		protected int itweenID;

		protected bool islooping;

		public override void Reset()
		{
		}

		protected void OnEnteriTween(FsmOwnerDefault anOwner)
		{
		}

		protected void IsLoop(bool aValue)
		{
		}

		private void iTweenOnStart(int aniTweenID)
		{
		}

		private void iTweenOnComplete(int aniTweenID)
		{
		}

		public void OnStart(itDic dic)
		{
		}

		public void OnUpdate(itDic dic)
		{
		}

		public void OnComplete(itDic dic)
		{
		}

		protected void OnExitiTween(FsmOwnerDefault anOwner)
		{
		}
	}
}
