namespace HutongGames.PlayMaker.Actions
{
	public class SelectRandomVector3 : FsmStateAction
	{
		public FsmVector3[] vector3Array;

		public FsmFloat[] weights;

		[RequiredField]
		public FsmVector3 storeVector3;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSelectRandomColor()
		{
		}
	}
}
