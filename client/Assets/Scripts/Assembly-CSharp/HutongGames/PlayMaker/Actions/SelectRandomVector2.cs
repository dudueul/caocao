namespace HutongGames.PlayMaker.Actions
{
	public class SelectRandomVector2 : FsmStateAction
	{
		public FsmVector2[] vector2Array;

		public FsmFloat[] weights;

		[RequiredField]
		public FsmVector2 storeVector2;

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
