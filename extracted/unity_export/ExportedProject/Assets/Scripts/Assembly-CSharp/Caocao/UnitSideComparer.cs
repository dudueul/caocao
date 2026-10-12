using System.Collections.Generic;

namespace Caocao
{
	internal class UnitSideComparer : IEqualityComparer<UnitSide>
	{
		bool IEqualityComparer<UnitSide>.Equals(UnitSide x, UnitSide y)
		{
			return false;
		}

		int IEqualityComparer<UnitSide>.GetHashCode(UnitSide obj)
		{
			return 0;
		}
	}
}
