import React from 'react';
import HeaderDropdown from '../HeaderDropdown';

export type GlobalHeaderRightProps = {
  menu?: boolean;
};

const AvatarDropdown: React.FC<GlobalHeaderRightProps> = ({}) => {
  return (
    <HeaderDropdown overlay={""}>
    </HeaderDropdown>
  );
};

export default AvatarDropdown;
